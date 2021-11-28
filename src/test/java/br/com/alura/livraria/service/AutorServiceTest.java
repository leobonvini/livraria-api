package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.alura.livraria.dto.AutorDTO;
import br.com.alura.livraria.dto.AutorFormDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
	
	@Mock
	private AutorRepository autorRepository;
	
	@InjectMocks
	private AutorService autorService;
	
	@Mock
	private ModelMapper modelMapper;

	@Test
	void deveriaCadastrarUmAutor() {
		AutorFormDTO formDTO = new AutorFormDTO(
				"Leonardo", 
				"leonardo@leonardo.com",
				LocalDate.of(1997, 5, 28),
				"Livros Genericos"
				);
		
		Autor autor = new Autor(formDTO.getNome(),
				formDTO.getEmail(),
				formDTO.getNascimento(),
				formDTO.getMiniCurriculo());
		
		Mockito
		.when(modelMapper.map(formDTO, Autor.class))
		.thenReturn(autor);
		
		Mockito
		.when(modelMapper.map(autor, AutorDTO.class))
		.thenReturn(new AutorDTO(
				null,
				autor.getNome(),
				autor.getEmail(),
				autor.getMiniCurriculo()));
		
		
		AutorDTO dto = autorService.cadastrar(formDTO);
		
		Mockito.verify(autorRepository).save(Mockito.any());
		
		assertEquals(formDTO.getNome(), dto.getNome());
		assertEquals(formDTO.getEmail(), dto.getEmail());
		assertEquals(formDTO.getMiniCurriculo(), dto.getMiniCurriculo());
		
	}

}
