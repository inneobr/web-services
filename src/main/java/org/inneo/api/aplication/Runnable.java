package org.inneo.api.aplication;

import lombok.AllArgsConstructor;
import org.inneo.api.service.WeatherService;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

@Component
@EnableScheduling
@AllArgsConstructor
public class Runnable {
	private final long time = 21600000;
	private WeatherService weatherService;
	
	@Scheduled(fixedDelay = time)
	public void getWeather() {
		weatherService.getWeather();
	}
}