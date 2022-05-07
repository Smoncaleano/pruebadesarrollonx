package com.nexxos.springboot.backend.apirest.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexxos.springboot.backend.apirest.models.dao.IMercanciaDao;
import com.nexxos.springboot.backend.apirest.models.entity.Mercancia;

@Service
public class MercanciaServiceImpl  implements IMercanciaService{

	@Autowired
	private IMercanciaDao mercanciaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Mercancia> findAll() {
		return (List<Mercancia>) mercanciaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Mercancia findById(Long id) {
		return mercanciaDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Mercancia findByNamee(String name) {
		return mercanciaDao.findByNamee(name); 
	}

	@Override
	@Transactional
	public Mercancia save(Mercancia mercancia) {
		return mercanciaDao.save(mercancia);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		mercanciaDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<Mercancia> findByUser(Long fkUser) {
		return mercanciaDao.findByUser(fkUser);
	}

	@Override
	public List<Mercancia> findByDate(Date fecha) {
		return mercanciaDao.findByDate(fecha);
	}

	@Override
	public List<Mercancia> findByDateAndUser(Date fecha, Long creador) {
		return mercanciaDao.findByDateAndUser(fecha, creador);
	}

}
