package org.inneo.api.domain.previsao;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import org.inneo.api.domain.GenericEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_weather")
public class Weather extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	private String temp;
	private String date;
	private String time;
	private String city;
	private String description;

}
