package org.inneo.api.aplication;


import java.util.List;
import lombok.AllArgsConstructor;

import org.inneo.api.service.PrevisaoService;
import org.springframework.stereotype.Component;
import org.inneo.api.domain.previsao.Forecity;

import org.inneo.api.repository.previsao.ForecityRep;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

@Component
@EnableScheduling
@AllArgsConstructor
public class Runnable {
	private final long run_time = 3600000;
	private PrevisaoService previsaoService;
	private ForecityRep forecityRep;
	
	
	@Scheduled(fixedDelay = run_time, 
			initialDelay = 60_000)
	public void getWeather() {
		List<Forecity> forecitys = forecityRep.findAll();
		if(forecitys != null) {
			for(Forecity forecity: forecitys) {
				previsaoService.getWeather(forecity.getCidadeEstado());
			}
		}		
	}	
}
