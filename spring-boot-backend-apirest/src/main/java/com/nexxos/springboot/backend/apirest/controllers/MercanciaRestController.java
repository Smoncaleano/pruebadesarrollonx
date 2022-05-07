package com.nexxos.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexxos.springboot.backend.apirest.models.entity.Mercancia;
import com.nexxos.springboot.backend.apirest.models.entity.Usuario;
import com.nexxos.springboot.backend.apirest.models.services.IMercanciaService;
import com.nexxos.springboot.backend.apirest.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/apiMercancia")
public class MercanciaRestController {
	
	@Autowired
	IMercanciaService mercanciaService;
	
	@Autowired
	IUsuarioService usuarioService;
	
	@GetMapping("/mercancias")
	public List<Mercancia> index() {
		return mercanciaService.findAll();
	}
	
	@GetMapping("/mercanciasUser/{fkUser}")
	public ResponseEntity<?> showByUser(@PathVariable Long fkUser) {
		System.out.println("entra");
		List<Mercancia> mercancia = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			mercancia = mercanciaService.findByUser(fkUser);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(mercancia == null || mercancia.size() <=0) {
			System.out.println("entra null");
			response.put("mensaje", "El usuario con id: ".concat(fkUser.toString().concat(" no eha creado un en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List>(mercancia, HttpStatus.OK);
	}
	

	@GetMapping("/mercanciasDate/{fecha}")
	public ResponseEntity<?> showByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha) {
		List<Mercancia> mercancia = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			mercancia = mercanciaService.findByDate(fecha);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(mercancia == null || mercancia.size()<=0) {
			response.put("mensaje", "La mercancia con fecha de creación: ".concat(fecha.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List>(mercancia, HttpStatus.OK);
	}
	
	
	@GetMapping("/mercanciasDateAndName/{fecha}/{fkUser}")
	public ResponseEntity<?> showByDateAndName(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha, @PathVariable Long fkUser) {
		List<Mercancia> mercancia = null;
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findById(fkUser);
		if(usuario!=null) {
			try {
				mercancia = mercanciaService.findByDateAndUser(fecha, fkUser);
				
				
			} catch(DataAccessException e) {
				response.put("mensaje", "Error al realizar la consulta en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			response.put("mensaje", "El usuario: ".concat(fkUser.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
		
		if(mercancia == null || mercancia.size()<=0) {
			response.put("mensaje", "La consulta no arrojó resultados: ");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List>(mercancia, HttpStatus.OK);
	}
	
	@GetMapping("/mercancias/{name}")
	public ResponseEntity<?> showByName(@PathVariable String name) {
		Mercancia mercancia = null;
		List<Mercancia> m = new ArrayList<>();
		Map<String, Object> response = new HashMap<>();
		name = name.toUpperCase();
		System.out.println("aaaaaaaa  " + name);
		try {
			mercancia = mercanciaService.findByNamee(name);
			
			m.add(mercancia);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(mercancia == null) {
			System.out.println("es null");
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("mensaje", "La mercancia: ".concat(name.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List>(m, HttpStatus.OK);
	}

	@GetMapping("/mercanciasId/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Mercancia mercancia = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			mercancia = mercanciaService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(mercancia == null) {
			response.put("mensaje", "La mercancia: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Mercancia>(mercancia, HttpStatus.OK);
	}
	
	@PostMapping("/mercancias/{fkUser}")
	public ResponseEntity<?> create(@Valid @RequestBody Mercancia mercancia, BindingResult result, @PathVariable Long fkUser) {
		
		Mercancia mercanciaNew = null;
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findById(fkUser);
		
		
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			if(usuario!=null) {
				mercancia.setNombre(mercancia.getNombre().toUpperCase());
				mercanciaNew = mercanciaService.save(mercancia);
			}else {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", "No existe en la base de datos el usuario");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La mercancía ha sido creada con éxito!");
		response.put("cliente", mercanciaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/mercancias/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Mercancia mercancia, BindingResult result, @PathVariable Long id) {

		Mercancia mercanciaActual = mercanciaService.findById(id);

		Mercancia mercanciaUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (mercanciaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la mercancía ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			mercanciaActual.setNombre(mercancia.getNombre().toUpperCase());
			mercanciaActual.setCantidad(mercancia.getCantidad());
			mercanciaActual.setCreateAt(mercancia.getCreateAt());
			mercanciaActual.setUsuario(mercancia.getUsuario());

			mercanciaUpdated = mercanciaService.save(mercanciaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la mercancía en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La mercancía ha sido actualizado con éxito!");
		response.put("cliente", mercanciaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/mercancias/{id}/{fkUser}")
	public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable Long fkUser) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			Mercancia mercanciaToDelete = mercanciaService.findById(id);
			
			if(mercanciaToDelete != null) {
				if(mercanciaToDelete.getUsuario().getId() != fkUser) {
					response.put("mensaje", "La identificacion del usuario creador de la mercancía no coincide");
					response.put("error", "La identificacion del usuario creador de la mercancía no coincide");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}else {
					mercanciaService.delete(id);
				}
			}else {
				response.put("mensaje", "La mercancía a borrar no existe");
				response.put("error", "La mercancía a borrar no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la mercancía de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La mercancía ha sido eliminado con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
