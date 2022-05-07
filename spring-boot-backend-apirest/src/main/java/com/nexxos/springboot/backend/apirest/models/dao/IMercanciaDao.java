package com.nexxos.springboot.backend.apirest.models.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nexxos.springboot.backend.apirest.models.entity.Mercancia;

public interface IMercanciaDao extends CrudRepository<Mercancia, Long> {
	
	@Query("SELECT m FROM Mercancia m WHERE m.nombre =:nombrej")
	Mercancia findByNamee(String nombrej); 
	
	@Query("SELECT m FROM Mercancia m WHERE m.usuario.id =:creador")
	List<Mercancia> findByUser(Long creador); 
	
	@Query("SELECT m FROM Mercancia m WHERE m.createAt =:fecha")
	List<Mercancia> findByDate(Date fecha);
	
	@Query("SELECT m FROM Mercancia m WHERE m.createAt =:fecha AND m.usuario.id =:creador")
	List<Mercancia> findByDateAndUser(Date fecha, Long creador);

}
