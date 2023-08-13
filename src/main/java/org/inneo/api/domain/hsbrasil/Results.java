package org.inneo.api.domain.hsbrasil;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.util.List;

import org.inneo.api.domain.GenericEntity;

import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_results")
public class Results extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	private String temp;
	private String date;
	private String time;
	private String description;
	private String currently;
	private String city;
	private String humidity;
	private String cloudiness;
	private String rain;
	private String wind_speedy;
	
	@Transient
	private List<Forecast> forecast;
}
