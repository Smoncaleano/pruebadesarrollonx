package com.nexxos.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/***
 * Entidad que modela un cargo
 * @author Sebastian Moncaleano
 *	06/06/2022
 */
@Entity
@Table(name="cargos")
@JsonIgnoreProperties(ignoreUnknown = true) 
public class Cargo implements Serializable {


	private static final long serialVersionUID = 1L;

	/**
	 * Atributo id de clase cargo
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("id")
	@Min(1)
	private Long id;
	
	/**
	 * Atributo nombre de clase cargo
	 */
	@NotEmpty(message ="no puede estar vacio")
	@Column(nullable=false)
	 @JsonProperty("nombre")
	private String nombre;

	
	
/***
 * Constructor con parámetros
 * @param id id de cargo
 * @param nombre nombre de cargo
 */
	public Cargo(Long id, @NotEmpty(message = "no puede estar vacio") String nombre) {
	
		this.id = id;
		this.nombre = nombre;
	}

	/***
	 * Constructor sin parámetros
	 */
	public Cargo() {
	
	}

	/**
	 Getters and setters
	 **/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
