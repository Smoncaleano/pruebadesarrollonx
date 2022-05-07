package com.nexxos.springboot.backend.apirest.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nexxos.springboot.backend.apirest.models.dao.ICargoDao;
import com.nexxos.springboot.backend.apirest.models.entity.Cargo;
import com.nexxos.springboot.backend.apirest.models.services.CargoServiceImpl;

public class UsuarioServiceImplTest {
	
	@Mock
	private ICargoDao cargoDao;
	
	@InjectMocks
	private CargoServiceImpl cargoService;
	
	private List<Cargo> cargos;
	
	Cargo cargo;
	Optional<Cargo> cargo2;
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		cargo = new Cargo();
		cargo2 = Optional.of(new Cargo());
		cargos = new ArrayList<>();
		cargo.setId(3L);
		cargo.setNombre("prueba");
		cargos.add(cargo);
		cargo2.get().setId(3L);;
		cargo2.get().setNombre("prueba2");
		
	}
	
	@Test
	public void findById() {
		
		when(cargoDao.findById(3L)).thenReturn(cargo2);
		Cargo cargoP = cargoService.findById(3L);
		assertEquals(cargo2.get().getId(), cargoP.getId());
		
	}
	
	
	@Test
	public void save() {
		
		when(cargoDao.save(cargo)).thenReturn(cargo);
		Cargo cargoP = cargoService.save(cargo);
		assertEquals(cargo.getId(), cargoP.getId());
		
	}
	
	@Test
	public void findAll() {
		
		when(cargoDao.findAll()).thenReturn(cargos);
		List<Cargo> cargo2 = cargoService.findAll();
		assertEquals(3L, cargo2.get(0).getId());
		
	}


}
