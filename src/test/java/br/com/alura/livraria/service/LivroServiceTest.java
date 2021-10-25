package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.LivroDTO;
import br.com.alura.livraria.dto.LivroFormDTO;
import br.com.alura.livraria.repository.AutorRepository;
import br.com.alura.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
	
	@Mock
	private AutorRepository autorRepository;
	
	@Mock
	private LivroRepository livroRepository;
	
	@InjectMocks
	private LivroService livroService;
	
	private LivroFormDTO criarFormDTO() {
		LivroFormDTO formDTO = new LivroFormDTO(
				"Livro",
				LocalDate.of(2021, 01, 01),
				100,
				1l);
		return formDTO;
	}

	@Test
	void deveriaCadastrarUmLivro() {
		LivroFormDTO formDTO = criarFormDTO();
		
		LivroDTO dto = livroService.cadastrar(formDTO);
		
		Mockito.verify(livroRepository).save(Mockito.any());
		
		assertEquals(formDTO.getTitulo(), dto.getTitulo());
		assertEquals(formDTO.getLancamento(), dto.getLancamento());
		assertEquals(formDTO.getNumeroDePaginas(), dto.getNumeroDePaginas());
		
	}
	
	void naoDeveriaCadastrarUmLivroComAutorInvalido() {
		LivroFormDTO formDTO = criarFormDTO();
		
		Mockito.when(autorRepository.getById(formDTO.getAutorId()))
		.thenThrow(EntityNotFoundException.class);
		
		assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar(formDTO));
	}
	

}
