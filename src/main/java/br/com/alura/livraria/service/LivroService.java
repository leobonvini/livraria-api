package br.com.alura.livraria.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.livraria.dto.LivroDTO;
import br.com.alura.livraria.dto.LivroFormDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	public Page<LivroDTO> listar(Pageable paginacao) {
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(t-> modelMapper.map(t, LivroDTO.class));
		
	}

	@Transactional
	public LivroDTO cadastrar(LivroFormDTO dto) {
		Livro livro = modelMapper.map(dto, Livro.class);
		Autor autor = autorRepository.getById(dto.getAutorId());
		
		livro.setId(null);
		livro.setAutor(autor);
		
		livroRepository.save(livro);
		return modelMapper.map(livro, LivroDTO.class);

	}

}
