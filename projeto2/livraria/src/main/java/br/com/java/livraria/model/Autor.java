package br.com.java.livraria.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Autor {

	private Long id;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private String miniCurriculo;

}
