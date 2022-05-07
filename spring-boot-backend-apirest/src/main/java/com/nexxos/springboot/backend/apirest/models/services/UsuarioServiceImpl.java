package com.nexxos.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexxos.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.nexxos.springboot.backend.apirest.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}
	
	

}
