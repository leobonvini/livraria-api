package br.com.alura.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.AutorDTO;
import br.com.alura.livraria.dto.AutorFormDTO;
import br.com.alura.livraria.modelo.Autor;

@Service
public class AutorService {

	private List<Autor> autores = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();

	public List<AutorDTO> listar() {
		return autores.stream().map(t -> modelMapper.map(t, AutorDTO.class)).collect(Collectors.toList());

	}

	public void cadastrar(AutorFormDTO dto) {
		Autor autor = modelMapper.map(dto, Autor.class);

		autores.add(autor);

	}
}