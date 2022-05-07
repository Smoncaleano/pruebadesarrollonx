package com.nexxos.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/***
 * Entidad que modela una mercancia
 * @author Sebastian Moncaleano
 *	06/06/2022
 */

@Entity
@Table(name="mercancias")
public class Mercancia implements Serializable {
	

	private static final long serialVersionUID = 1L;

	/**
	 * Atributo id de clase mercancia
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	/**
	 * Atributo nombre de clase mercancia
	 */
	@NotEmpty(message ="no puede estar vacio")
	@Size(min=3, max=100, message="el tamaño tiene que estar entre 4 y 12")
	@Column(nullable=false, unique=true)
	private String nombre;
	
	/**
	 * Atributo cantidad de clase mercancia
	 */
	@NotNull
	@Min(1)
	@Max(100)
	@Column(nullable=false)
	private Integer cantidad;
	
	/**
	 * Atributo createAt de clase mercancia
	 */
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	@NotNull
	@PastOrPresent
	private Date createAt;
	
	/**
	 * Atributo Usuario de clase mercancia, se hace referencia a la llave foránea
	 */
	@JoinColumn(name = "fk_usuario_creador", nullable = false)
	@ManyToOne
	private Usuario usuario;

	
	/**
	 * Getters and Setters
	 */
	
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Mercancia() {
		
	}
	
	

}
