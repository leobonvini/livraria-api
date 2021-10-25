package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.jayway.jsonpath.JsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class LivroControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@Test
	void naoDeveriaCadastrarLivroComDadosIncompletos() throws Exception {
		String json = "{}";
		mvc
		.perform(post("/livros")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void deveriaCadastrarLivroComDadosCompletos() throws Exception {
		String jsonAutor = "{\"nome\":\"Fulano da Silva\","
				+ "\"email\":\"fulano@fulano.com\","
				+ "\"nascimento\":\"1990-01-01\",\"miniCurriculo\":\"Livros genericos\"}";
		
		String jsonAutorRetornado = "{\"nome\":\"Fulano da Silva\",\"email\":\"fulano@fulano.com\",\"miniCurriculo\":\"Livros genericos\"}";
		
		MvcResult resultado = mvc
		.perform(post("/autores")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonAutor))
		.andExpect(status().isCreated())
		.andExpect(header().exists("Location"))
		.andExpect(content().json(jsonAutorRetornado))
		.andReturn();
		
		
		Integer id = JsonPath.read(resultado.getResponse().getContentAsString(), "$.id");
				
		
		
		String jsonLivro = "{\"titulo\":\"Livro Generico\",\"lancamento\":\"2020-01-01\",\"numeroDePaginas\":100,\"autor_id\":"+id+"}";
		
		String jsonLivroRetornado = "{\"titulo\": \"Livro Generico\","
				+ "\"lancamento\": \"2020-01-01\","
				+ "\"numeroDePaginas\": 100,"
				+ "\"autor\": {\"id\": "+id+"}}";

		mvc
		.perform(post("/livros")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonLivro))
		.andExpect(status().isCreated())
		.andExpect(header().exists("Location"))
		.andExpect(content().json(jsonLivroRetornado))
		;
	}
}
