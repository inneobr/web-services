package org.inneo.api.domain.hsbrasil;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import lombok.NoArgsConstructor;

import org.inneo.api.domain.GenericEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_clima_next")
public class Forecast extends GenericEntity{
	private static final long serialVersionUID = 1L;
		
	private String max;
	private String min;
	private String rain;
	private String date;
	private String city;
	private String weekday;
	private String condition;
	private String cloudiness;
	private String wind_speedy;
	private String description;
	private String rain_probability;
}
