package com.nexxos.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.nexxos.springboot.backend.apirest.models.entity.Cargo;

public interface ICargoDao extends JpaRepository<Cargo, Long>{

}
