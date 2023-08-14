package org.inneo.api.service;

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
	private WebService weatherService;
	private ForecastRep forecastRep;
	private ResultsRep resultsRep;
	
	
	public void getWeather(String cidade, String estado) {		
		
		HsBrasil response = weatherService.getWeather(cidade, estado);
		if(response != null) {
			String municipio = response.getResults().getCity().replace(" ", "");
			response.getResults().setCity(municipio);
			if(response.getResults() != null) {
				Results create = resultsRep.findByCityAndDate(response.getResults().getCity(), response.getResults().getDate());
				if(create != null) {
					BeanUtils.copyProperties(response.getResults(), create);
					resultsRep.save(create);
				}
				else {
					resultsRep.save(response.getResults());
				}
			}
			
			if(response.getResults().getForecast() != null) {
				for(Forecast forecast: response.getResults().getForecast()) {
					forecast.setCity(response.getResults().getCity());
					Forecast create = forecastRep.findByCityAndDate(response.getResults().getCity(),forecast.getDate());
					if(create != null) {						
						BeanUtils.copyProperties(forecast, create);
						forecastRep.save(create);
					}else {
						forecastRep.save(forecast);
					}
				}
			}
		}
	}
}