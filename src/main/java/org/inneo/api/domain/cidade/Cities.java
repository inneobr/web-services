package org.inneo.api.domain.cidade;

import org.inneo.api.domain.GenericEntity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_cities")
public class Cities extends GenericEntity{
		private static final long serialVersionUID = 1L;
		
		@NonNull
		@Column(name = "codigo", unique = true)
		private Long codigo;
		
		@NonNull
		@Column(name = "cidade")
		private String cidade;	
		
		@NonNull
		@Column(name = "estado")
		private String estado;
		
		public String getCidadeUf() {
			return cidade + "," + estado;
		}
}
