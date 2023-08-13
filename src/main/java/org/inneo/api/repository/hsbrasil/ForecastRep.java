package org.inneo.api.repository;

import org.inneo.api.domain.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRep extends JpaRepository<Forecast, Long>{
	Forecast findByCityAndDate(String city, String date);

}
