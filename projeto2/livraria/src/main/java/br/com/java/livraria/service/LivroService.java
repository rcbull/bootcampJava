package br.com.java.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.java.livraria.dto.LivroDto;
import br.com.java.livraria.dto.LivroFormDto;
import br.com.java.livraria.model.Autor;
import br.com.java.livraria.model.Livro;

@Service
public class LivroService {

	private List<Livro> livros = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();

	public List<LivroDto> listar() {

		return livros.stream().map(t -> modelMapper.map(t, LivroDto.class)).collect(Collectors.toList());
	}

	public void cadastrar(LivroFormDto dto) {

		Livro livro = new ModelMapper().map(dto, Livro.class);
		
//		livro.setAutor(new Autor());

		livros.add(livro);

	}

}
