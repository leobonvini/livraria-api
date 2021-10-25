package br.com.alura.livraria.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livraria.dto.QuantidadeDeLivrosDTO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {
	
	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	void deveriaRetornarRelatorioDeLivros() throws Exception {
		Autor a1 = new Autor("Leonardo",
				"leonardo@leonardo.com", 
				LocalDate.of(1997, 5, 28), 
				"Livros diversos");
		
		em.persist(a1);
		
		Autor a2 = new Autor("Mariana",
				"mariana@mariana.com", 
				LocalDate.of(2000, 9, 2), 
				"Diversos livros");
		
		em.persist(a2);
		
		Livro l1 = new Livro ("Livro 1", 
				LocalDate.of(2021, 01, 01),
				100,
				a1);
		
		em.persist(l1);
		
		Livro l2 = new Livro ("Livro 2", 
				LocalDate.of(2021, 01, 01),
				100,
				a1);
		
		em.persist(l2);
		
		Livro l3 = new Livro ("Livro 3", 
				LocalDate.of(2021, 01, 01),
				100,
				a2);
		
		em.persist(l3);
		
		List<QuantidadeDeLivrosDTO> relatorio = repository.relatorioQuantidadeDeLivros();
		Assertions
		.assertThat(relatorio)
		.hasSize(2)
		.extracting(QuantidadeDeLivrosDTO::getAutor,
				QuantidadeDeLivrosDTO::getQuantidade, 
				QuantidadeDeLivrosDTO::getPercentual)
		.containsExactlyInAnyOrder(
				Assertions.tuple("Leonardo", 2l, 66.67),
				Assertions.tuple("Mariana", 1l, 33.33));
		
	}

}
