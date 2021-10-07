package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorFormDTO {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@Past
	private LocalDate nascimento;
	
	@NotBlank
	private String miniCurriculo;
}
