package com.nexxos.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexxos.springboot.backend.apirest.models.dao.ICargoDao;
import com.nexxos.springboot.backend.apirest.models.entity.Cargo;

@Service
public class CargoServiceImpl implements ICargoService {

	@Autowired
	private ICargoDao cargoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> findAll() {
		return (List<Cargo>) cargoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cargo findById(Long id) {
		return cargoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public Cargo save(Cargo cargo) {
		return cargoDao.save(cargo);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		cargoDao.deleteById(id);
		
	}
	


	

}
