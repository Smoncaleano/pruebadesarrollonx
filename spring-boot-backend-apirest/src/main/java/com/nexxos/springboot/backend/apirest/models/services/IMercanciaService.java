package com.nexxos.springboot.backend.apirest.models.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nexxos.springboot.backend.apirest.models.entity.Mercancia;

public interface IMercanciaService {
	
	public List<Mercancia> findAll();
	
	public Mercancia findById(Long id);
	
	public Mercancia save(Mercancia mercancia);
	
	public void delete(Long id);
	
	public Mercancia findByNamee(String name);
	
	public List<Mercancia> findByUser(Long id);

	public List<Mercancia> findByDate(Date fecha);
	
	public List<Mercancia> findByDateAndUser(Date fecha, Long creador);

}
