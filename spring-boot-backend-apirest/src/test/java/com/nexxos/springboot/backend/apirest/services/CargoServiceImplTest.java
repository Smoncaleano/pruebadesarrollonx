package com.nexxos.springboot.backend.apirest.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.nexxos.springboot.backend.apirest.models.dao.IUsuarioDao;

import com.nexxos.springboot.backend.apirest.models.entity.Usuario;

import com.nexxos.springboot.backend.apirest.models.services.UsuarioServiceImpl;

public class CargoServiceImplTest {
	
	@Mock
	private IUsuarioDao usuarioDao;
	
	@InjectMocks
	private UsuarioServiceImpl usuarioService;
	
	private List<Usuario> usuarios;
	
	Usuario usuario;
	Optional<Usuario> usuario2;
	
	@BeforeEach
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		usuario = new Usuario();
		usuario2 = Optional.of(new Usuario());
		usuarios = new ArrayList<>();
		usuario.setId(3L);
		usuario.setNombre("prueba");
		usuarios.add(usuario);
		usuario2.get().setId(3L);;
		usuario2.get().setNombre("prueba2");
		
	}
	
	@Test
	public void findById() {
		
		when(usuarioDao.findById(3L)).thenReturn(usuario2);
		Usuario usuarioP = usuarioService.findById(3L);
		assertEquals(usuario2.get().getId(), usuarioP.getId());
		
	}
	
	
	@Test
	public void save() {
		
		when(usuarioDao.save(usuario)).thenReturn(usuario);
		Usuario usuarioP = usuarioService.save(usuario);
		assertEquals(usuario.getId(), usuarioP.getId());
		
	}
	
	@Test
	public void findAll() {
		
		when(usuarioDao.findAll()).thenReturn(usuarios);
		List<Usuario> usuario21 = usuarioService.findAll();
		assertEquals(3L, usuario21.get(0).getId());
		
	}


}
