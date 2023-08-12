package org.inneo.api.domain;

import lombok.Getter;
import java.util.Date;
import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@MappedSuperclass
public abstract class GenericEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@CreationTimestamp
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Date published;

}
