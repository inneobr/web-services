package org.inneo.api.repository.cities;

import org.inneo.api.domain.cidade.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRep extends JpaRepository<Cities, Long>{
	Cities findByCodigo(Long id);	
}