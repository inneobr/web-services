package org.inneo.api.aplication;

import lombok.AllArgsConstructor;

import java.util.List;

import org.inneo.api.domain.cidade.Cities;
import org.inneo.api.repository.cities.CitiesRep;
import org.inneo.api.service.WeatherService;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

@Component
@EnableScheduling
@AllArgsConstructor
public class Runnable {
	private final long run_time = 14400000;
	private WeatherService weatherService;
	private CitiesRep citiesRep;
	
	
	@Scheduled(fixedDelay = run_time, 
			initialDelay = 0_000)
	public void getWeather() {
		List<Cities> response = citiesRep.findAll();
		if(response != null) {
			for(Cities cities: response) {
				weatherService.getWeather(cities.getCidadeUf());
			}
		}		
	}	
}
