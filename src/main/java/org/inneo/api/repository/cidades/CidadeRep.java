package org.inneo.api.repository.cidades;

import org.inneo.api.domain.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRep extends JpaRepository<Cidade, Long>{
	Cidade findByCodigo(Long id);	
}