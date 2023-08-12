package org.inneo.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;

import org.inneo.api.domain.Results;
import org.inneo.api.web.WebService;
import org.inneo.api.domain.Forecast;
import org.inneo.api.domain.HsBrasil;

import org.inneo.api.repository.ForecastRep;
import org.inneo.api.repository.ResultsRep;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class WeatherService {
	private static Logger logger = LoggerFactory.getLogger(WeatherService.class);
	private WebService weatherService;
	private ForecastRep forecastRep;
	private ResultsRep resultsRep;
	
	public void getWeather() {
		HsBrasil response = weatherService.getWeather("Palmas", "PR");
		if(response != null) {
			if(response.getResults() != null) {
				Results create = resultsRep.findByCityAndDate(response.getResults().getCity(), response.getResults().getDate());
				if(create != null) {
					logger.info("Previsão do tempo atualizada: {}", response.getResults().getPublished());
					BeanUtils.copyProperties(response.getResults(), create);
					resultsRep.save(create);
				}
				else {
					logger.info("Previsão do tempo cadastrada: {}", response.getResults().getPublished());
					resultsRep.save(response.getResults());
				}
			}
			
			if(response.getResults().getForecast() != null) {
				for(Forecast forecast: response.getResults().getForecast()) {
					Forecast create = forecastRep.findByCityAndDate(response.getResults().getCity(), response.getResults().getDate());
					if(create != null) {
						BeanUtils.copyProperties(forecast, create);
						forecastRep.save(create);
						logger.info("Data atualizada cadastrado: {}", response.getResults().getPublished());
					}else {
						forecastRep.save(forecast);
						logger.info("Nova data cadastrada: {}", response.getResults().getPublished());
					}
				}
			}
		}
	}
}