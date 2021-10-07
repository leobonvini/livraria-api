package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.LivroDTO;
import br.com.alura.livraria.dto.LivroFormDTO;
import br.com.alura.livraria.modelo.Livro;

@Service
public class LivroService {
	
	private List<Livro> livros = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();

	public List<LivroDTO> listar() {
		return livros.stream().map(t -> modelMapper.map(t, LivroDTO.class)).collect(Collectors.toList());

	}

	public void cadastrar(LivroFormDTO dto) {
		Livro livro = modelMapper.map(dto, Livro.class);

		livros.add(livro);

	}

}
