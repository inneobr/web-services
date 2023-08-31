package org.inneo.api.web;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Transient;
import org.inneo.api.domain.GenericEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	private List<WForecast> forecast;
}
