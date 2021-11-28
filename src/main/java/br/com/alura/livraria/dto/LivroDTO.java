package br.com.alura.livraria.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {

	private Long id;
	private String titulo;
	private LocalDate lancamento;
	private int numeroDePaginas;
	private AutorDTO autor;
}
