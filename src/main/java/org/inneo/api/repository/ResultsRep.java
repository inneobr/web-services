package org.inneo.api.repository;

import org.inneo.api.domain.Results;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultsRep extends JpaRepository<Results, Long>{
	Results findByCityAndDate(String city, String date);
}
