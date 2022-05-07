package com.nexxos.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nexxos.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

}
