package br.com.alura.livraria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.AutorDTO;
import br.com.alura.livraria.dto.AutorFormDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	private ModelMapper modelMapper = new ModelMapper();

	public Page<AutorDTO> listar(Pageable paginacao) {
		Page<Autor> autores = autorRepository.findAll(paginacao); 
		return autores.map(t -> modelMapper.map(t, AutorDTO.class));

	}

	public AutorDTO cadastrar(AutorFormDTO dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		
		autorRepository.save(autor);
		return modelMapper.map(autor, AutorDTO.class);

	}
}