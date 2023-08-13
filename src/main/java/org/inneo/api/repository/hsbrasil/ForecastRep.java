package org.inneo.api.repository.hsbrasil;

import org.inneo.api.domain.hsbrasil.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRep extends JpaRepository<Forecast, Long>{
	Forecast findByCityAndDate(String city, String date);

}
