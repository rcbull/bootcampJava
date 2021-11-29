package br.com.java.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorFormDto {

	@NotBlank
	@Size(min = 10)
	private String nome;
	@NotBlank
	private String email;
	@Past
	private LocalDate dataNascimento;
	@NotBlank
	private String miniCurriculo;
}
