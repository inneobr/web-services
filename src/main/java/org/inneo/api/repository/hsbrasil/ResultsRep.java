package org.inneo.api.repository.hsbrasil;

import org.inneo.api.domain.hsbrasil.Results;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultsRep extends JpaRepository<Results, Long>{
	Results findByCityAndDate(String city, String date);
}
