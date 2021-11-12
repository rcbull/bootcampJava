package br.com.java.livraria.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Livro {

	private Long id;
	private String titulo;
	private LocalDate dataLancamento;
	private Integer numeroDePaginas;
	private String autor;

}