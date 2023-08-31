package org.inneo.api.repository.previsao;

import org.inneo.api.domain.previsao.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRep extends JpaRepository<Forecast, Long>{
	Forecast findByCityAndDate(String city, String date);
}
