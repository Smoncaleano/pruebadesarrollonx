package com.nexxos.springboot.backend.apirest.models.services;

import java.util.List;

import com.nexxos.springboot.backend.apirest.models.entity.Cargo;

public interface ICargoService {
	
	public List<Cargo> findAll();
	
	public Cargo findById(Long id);
	
	public Cargo save(Cargo cargo);
	
	public void delete(Long id);

}
