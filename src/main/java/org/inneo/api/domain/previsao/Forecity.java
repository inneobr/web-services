package org.inneo.api.domain.previsao;

import org.inneo.api.domain.GenericEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_forecity")
public class Forecity extends GenericEntity{
	private static final long serialVersionUID = 1L;
	private String cidade;
	private String estado;
	
	public String getCidadeEstado() {
		return cidade + ","+ estado;
	}
}
