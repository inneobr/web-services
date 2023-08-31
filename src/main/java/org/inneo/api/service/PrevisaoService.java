package org.inneo.api.service;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.inneo.api.web.HsBrasil;
import org.inneo.api.web.WForecast;

import org.inneo.api.web.WebService;
import org.springframework.beans.BeanUtils;
import org.inneo.api.domain.previsao.Forecast;
import org.inneo.api.domain.previsao.Weather;

import org.springframework.stereotype.Service;
import org.inneo.api.repository.previsao.WeatherRep;
import org.inneo.api.repository.previsao.ForecastRep;

@Service
@AllArgsConstructor
public class PrevisaoService {
	private ForecastRep forecastRep;
	private WeatherRep weatherRep;
	private WebService webervice;
	
	public void getWeather(String cidade) {	
		
		HsBrasil hsbrasil = webervice.getWeather(cidade);			
		if(hsbrasil.getResults() != null) {
			Weather weather = Weather.builder()					
					.city(hsbrasil.getResults().getCity())
					.time(hsbrasil.getResults().getTime())
					.date(hsbrasil.getResults().getDate())
					.temp(hsbrasil.getResults().getTemp()+" ºC")
					.description(hsbrasil.getResults().getDescription())
					.build();
			weatherRep.save(weather);

			for(WForecast wcast: hsbrasil.getResults().getForecast()) {
				if(wcast != null) {
					var calendar = LocalDate.now();
					Forecast forecast = Forecast.builder()
							.max(wcast.getMax()+" ºC")
							.min(wcast.getMin()+" ºC")
							.weekday(wcast.getWeekday())
							.description(wcast.getDescription())
							.city(hsbrasil.getResults().getCity())
							.date(wcast.getDate()+"/"+calendar.getYear())
							.build();	
					
					Forecast create = forecastRep.findByCityAndDate(hsbrasil.getResults().getCity(), wcast.getDate());
					if(create == null) create = new Forecast();
					BeanUtils.copyProperties(forecast, create);
					forecastRep.save(create);
				}else {
					System.out.println("Previsao nula");
				}
			}
		}
	}
}