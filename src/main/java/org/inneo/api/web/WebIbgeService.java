package org.inneo.api.web;

import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class WebIbgeService {
	private static final String api_base_url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/PR/subdistritos";
	
	private WebClient webClient;
	
	public WebIbgeService(WebClient.Builder builder) {
		this.webClient = builder.baseUrl(api_base_url) .defaultCookie("cookieKey", "cookieValue")
				  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				  .build();
	}
	
	public Object getMunicipios() {
		return webClient.method(HttpMethod.GET)
				.uri("?orderBy=sigla")
				.retrieve()	
				.bodyToFlux(Object.class)
				.collectList()
				.block();
	}
}
