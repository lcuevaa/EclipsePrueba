package com.cueva.denuncia_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cueva.denuncia_service.entity.Denuncia;
import com.cueva.denuncia_service.service.DenunciaService;

@RestController
@RequestMapping("/v1/denuncia")
public class DenunciaController {
	@Autowired
	private DenunciaService service;
	@GetMapping
	public ResponseEntity<List<Denuncia>> findAll(
			@RequestParam(value="offset", required = false, defaultValue="0")int pageNumber,
			@RequestParam(value="limit", required = false, defaultValue="5")int pageSize){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Denuncia> registros = service.findAll(page);
		return new ResponseEntity<>(registros, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Denuncia> getById(@PathVariable("id")int id){
		Denuncia registro = service.findById(id);
		return new ResponseEntity<>(registro,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Denuncia>create(@RequestBody Denuncia registro){
		Denuncia producto = service.save(registro);
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Denuncia>update(@PathVariable ("id")int id, @RequestBody Denuncia registro){
		Denuncia producto = service.save(registro);
		return new ResponseEntity<>(producto, HttpStatus.OK); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable("id")int id, @RequestBody Denuncia registro){
		service.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
