package org.inneo.api.aplication;

import lombok.AllArgsConstructor;

import java.util.List;

import org.inneo.api.domain.cidade.Cidade;
import org.inneo.api.repository.cidades.CidadeRep;
import org.inneo.api.service.WeatherService;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

@Component
@EnableScheduling
@AllArgsConstructor
public class Runnable {
	private final long midday = 21600000;
	private WeatherService weatherService;
	private CidadeRep cidadeRep;
	
	@Scheduled(fixedDelay = midday)
	public void getWeather() {
		List<Cidade> cidades = cidadeRep.findAll();
		if(cidades != null) {
			for(Cidade cidade: cidades) {
				weatherService.getWeather(cidade.getCidade(), cidade.getEstado());
			}
		}
		
	}	
}
