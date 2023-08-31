package org.inneo.api.repository.previsao;

import org.inneo.api.domain.previsao.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRep extends JpaRepository<Weather, Long>{
	Weather findByCityAndDate(String city, String date);
}
