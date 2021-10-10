package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroFormDTO {

	@NotBlank
	@Size(min = 10)
	private String titulo;
	
	@PastOrPresent
	private LocalDate lancamento;
	
	@DecimalMin(value="100")
	private int numeroDePaginas;
	
	@JsonAlias("autor_id")
	private Long autorId;
}
