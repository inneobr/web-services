package org.inneo.api.aplication;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;

import org.inneo.api.service.PrevisaoService;
import org.inneo.api.domain.previsao.Forecity;
import org.springframework.stereotype.Component;

import org.inneo.api.repository.previsao.ForecityRep;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

@Component
@EnableScheduling
@AllArgsConstructor
public class Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Runnable.class);
	private final long timer = 1_800_000; //timer intervalo de 30 min: 1800000ms
	private PrevisaoService previsaoService;
	private ForecityRep forecityRep;	
	
	@Scheduled(fixedDelay = timer, initialDelay = 30_000)
	public void getWeather() {
		try {
			List<Forecity> forecitys = forecityRep.findAll();	
			
			if(forecitys != null) {
				for(Forecity forecity: forecitys) {
					previsaoService.getWeather(forecity.getCidadeEstado());
				}
			}
		}catch(NullPointerException e) {
			logger.error("HG-WEATHER recusou-se a fornecer dados meteorologia.");
		}
	}		
	
	
	
}
