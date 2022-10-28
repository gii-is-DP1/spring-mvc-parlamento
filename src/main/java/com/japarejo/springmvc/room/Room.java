package com.japarejo.springmvc.room;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long id;

	private Boolean active;

	@NotBlank
	private String description;
}
