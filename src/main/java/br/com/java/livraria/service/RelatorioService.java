package br.com.java.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.java.livraria.dto.RelatorioLivrosDto;
import br.com.java.livraria.repository.LivroRepository;

@Service
public class RelatorioService {

	@Autowired
	private LivroRepository repository;

	public List<RelatorioLivrosDto> relatorioQuantidadeDeLivros() {
		return repository.relatorioQuantidadeDeLivros();
	}
}
