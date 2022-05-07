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
import com.nexxos.springboot.backend.apirest.models.dao.IMercanciaDao;
import com.nexxos.springboot.backend.apirest.models.entity.Cargo;
import com.nexxos.springboot.backend.apirest.models.entity.Mercancia;
import com.nexxos.springboot.backend.apirest.models.services.CargoServiceImpl;
import com.nexxos.springboot.backend.apirest.models.services.MercanciaServiceImpl;

public class MercanciaServiceImplTest {
	
	@Mock
	private IMercanciaDao mercanciaDao;
	
	@InjectMocks
	private MercanciaServiceImpl mercanciaService;
	
	private List<Mercancia> mercancias;
	
	Mercancia mercancia;
	Optional<Mercancia> mercancia2;
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		mercancia = new Mercancia();
		mercancia2 = Optional.of(new Mercancia());
		mercancias = new ArrayList<>();
		mercancia.setId(3L);
		mercancia.setNombre("prueba");
		mercancias.add(mercancia);
		mercancia2.get().setId(3L);;
		mercancia2.get().setNombre("prueba2");
		
	}
	
	@Test
	public void findById() {
		
		when(mercanciaDao.findById(3L)).thenReturn(mercancia2);
		Mercancia mercanciaP = mercanciaService.findById(3L);
		assertEquals(mercancia2.get().getId(), mercanciaP.getId());
		
	}
	
	
	@Test
	public void save() {
		
		when(mercanciaDao.save(mercancia)).thenReturn(mercancia);
		Mercancia mercanciaP= mercanciaService.save(mercancia);
		assertEquals(mercancia.getId(), mercanciaP.getId());
		
	}
	
	@Test
	public void findAll() {
		
		when(mercanciaDao.findAll()).thenReturn(mercancias);
		List<Mercancia> mercancia21 = mercanciaService.findAll();
		assertEquals(3L, mercancia21.get(0).getId());
		
	}


}
