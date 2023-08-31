package org.inneo.api.web.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.inneo.api.domain.GenericEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDto extends GenericEntity{
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
