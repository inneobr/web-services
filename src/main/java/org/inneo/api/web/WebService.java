package org.inneo.api.web;

import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebService {
	private static final String api_base_url = "https://api.hgbrasil.com/weather?";
	private static final String api_base_cidade = "key=c2b5e8be&city_name=";
	
	private WebClient webClient;
	
	public WebService(WebClient.Builder builder) {
		this.webClient = builder.baseUrl(api_base_url) .defaultCookie("cookieKey", "cookieValue")
				  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				  .build();
	}
	
	public HsBrasil getWeather(String cidade) {
		StringBuilder uri = new StringBuilder();
		uri.append('?');
		uri.append(api_base_cidade);
		uri.append(cidade);
		
		Mono<HsBrasil> previsaoTempo = webClient.method(HttpMethod.GET)
				.uri(uri.toString())
				.retrieve()	
				.bodyToMono(HsBrasil.class);
		
		HsBrasil weather = previsaoTempo.block();
		return  weather;
	}
}
