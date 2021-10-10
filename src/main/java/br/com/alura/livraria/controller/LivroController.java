package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.LivroDTO;
import br.com.alura.livraria.dto.LivroFormDTO;
import br.com.alura.livraria.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService service;

	@GetMapping
	public Page<LivroDTO> listar(@PageableDefault(size=10) Pageable paginacao) {
		return service.listar(paginacao);

	}

	@PostMapping
	public ResponseEntity<LivroDTO> cadastrar(@RequestBody @Valid LivroFormDTO dto, 
			UriComponentsBuilder uriBuilder) {
		LivroDTO livroDTO = service.cadastrar(dto);
		
		URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livroDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(livroDTO);
	}

}