package br.com.java.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RelatorioLivrosDto {

	private String autor;
	private Long quantidade;
	private Double percentual;
}
