package com.nexxos.springboot.backend.apirest.models.services;

import java.util.List;

import com.nexxos.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario cliente);
	
	public void delete(Long id);

}
