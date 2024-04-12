package com.generation.farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	public CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {

		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll());
	}

	@GetMapping("/{id}")

	public ResponseEntity<Optional<Categoria>> getById(@PathVariable Long id) {

		Optional<Categoria> categoriaOp = categoriaRepository.findById(id);
		if (categoriaOp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(categoriaOp);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> getAll(@PathVariable String nome) {

		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody @Valid Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}

	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {

		Optional<Categoria> c = categoriaRepository.findById(categoria.getId());
		if (c.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATEGORIA NÃO EXISTE");
		}

		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Categoria> c = categoriaRepository.findById(id);
		if (c.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATEGORIA NÃO EXISTE");
		}
		categoriaRepository.deleteById(id);
	}

}
