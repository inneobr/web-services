package org.inneo.api.web.weather;

import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {
	private static final String api_base_url = "https://api.hgbrasil.com/weather?";
	private static final String api_city = "&city_name=";
	private static final String api_key1 = "key=e15f24da";
	private static final String api_key2 = "key=c2b5e8be";
	 
	private Boolean optional = false;
	private String token = null;
	
	
	private WebClient webClient;
	
	public WeatherService(WebClient.Builder builder) {
		this.webClient = builder.baseUrl(api_base_url) .defaultCookie("cookieKey", "cookieValue")
				  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				  .build();
	}
	
	public WeatherDto getWeather(String cidade) {
		if(optional == false) token = api_key1;
		if(optional == true ) token = api_key2;		
		optional = !optional;
		
		try {
			StringBuilder uri = new StringBuilder();
			uri.append('?');
			uri.append(token);
			uri.append(api_city);
			uri.append(cidade);			
			
			Mono<WeatherDto> previsaoTempo = webClient.method(HttpMethod.GET)
					.uri(uri.toString())
					.retrieve()	
					.bodyToMono(WeatherDto.class);
			
			WeatherDto weather = previsaoTempo.block();
			return  weather;
			
		}catch(Exception e) {
			throw new NullPointerException("HG-WEATHER: limite de requisicoes atingido.");
		}
	}
}
