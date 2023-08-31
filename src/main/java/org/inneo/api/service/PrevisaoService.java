package org.inneo.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import org.inneo.api.web.WebService;
import org.springframework.beans.BeanUtils;

import org.inneo.api.domain.hsbrasil.Results;
import org.springframework.stereotype.Service;
import org.inneo.api.domain.hsbrasil.Forecast;
import org.inneo.api.domain.hsbrasil.HsBrasil;

import org.inneo.api.repository.hsbrasil.ResultsRep;
import org.inneo.api.repository.hsbrasil.ForecastRep;

@Service
@AllArgsConstructor
public class WeatherService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	private WebService weatherService;
	private ForecastRep forecastRep;
	private ResultsRep resultsRep;
	
	
	public void getWeather(String cities) {		
		
		HsBrasil response = weatherService.getWeather(cities);
		if(response != null) {
			response.getResults().setCity(cities);
			if(response.getResults() != null) {
				Results create = resultsRep.findByCityAndDate(response.getResults().getCity(), response.getResults().getDate());
				if(create != null) {
					BeanUtils.copyProperties(response.getResults(), create);
					resultsRep.save(create);
				}
				else {
					resultsRep.save(response.getResults());
				}
				logger.info("Provision for today successfully saves.");
			}
			
			if(response.getResults().getForecast() != null) {
				for(Forecast forecast: response.getResults().getForecast()) {
					forecast.setCity(cities);
					Forecast create = forecastRep.findByCityAndDate(response.getResults().getCity(),forecast.getDate());
					if(create != null) {						
						BeanUtils.copyProperties(forecast, create);
						forecastRep.save(create);
					}else {
						forecastRep.save(forecast);
					}
				}
				logger.info("weekly forecast save successfully.");
			}
		}else {
			logger.error("Daily limit of depleted records!");
		}
	}
}