package org.inneo.api.service;

import org.slf4j.Logger;
import java.time.LocalDate;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;

import org.inneo.api.web.weather.WeatherDto;
import org.springframework.beans.BeanUtils;
import org.inneo.api.web.weather.ForecastDto;
import org.inneo.api.web.weather.WeatherService;

import org.inneo.api.domain.previsao.Weather;
import org.springframework.stereotype.Service;
import org.inneo.api.domain.previsao.Forecast;
import org.inneo.api.repository.previsao.WeatherRep;
import org.inneo.api.repository.previsao.ForecastRep;

@Service
@AllArgsConstructor
public class PrevisaoService {
	private static final Logger logger = LoggerFactory.getLogger(PrevisaoService.class);
	private WeatherService weatherService;
	private ForecastRep forecastRep;
	private WeatherRep weatherRep;
	
	public void getWeather(String cidade) {			
		WeatherDto weatherDto = weatherService.getWeather(cidade);			
		if(weatherDto.getResults() != null) {
			Weather weather = Weather.builder()					
					.city(weatherDto.getResults().getCity())
					.time(weatherDto.getResults().getTime())
					.date(weatherDto.getResults().getDate())
					.temp(weatherDto.getResults().getTemp()+" ºC")
					.description(weatherDto.getResults().getDescription())
					.build();
			weatherRep.save(weather);

			for(ForecastDto wcast: weatherDto.getResults().getForecast()) {
				if(wcast != null) {
					var calendar = LocalDate.now();
					Forecast forecast = Forecast.builder()
							.max(wcast.getMax()+" ºC")
							.min(wcast.getMin()+" ºC")
							.weekday(wcast.getWeekday())
							.description(wcast.getDescription())
							.city(weatherDto.getResults().getCity())
							.date(wcast.getDate()+"/"+calendar.getYear())
							.build();	
					
					Forecast create = forecastRep.findByCityAndDate(weatherDto.getResults().getCity(), wcast.getDate()+"/"+calendar.getYear());
					if(create == null) create = new Forecast();
					BeanUtils.copyProperties(forecast, create);
					forecastRep.save(create);
				}
			}
		}else {
			logger.error("HG-BRASIL: Não foi possivel carregar os dados.");
		}
	}
}