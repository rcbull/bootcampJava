package br.com.java.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.java.livraria.dto.AutorDto;
import br.com.java.livraria.dto.AutorFormDto;
import br.com.java.livraria.model.Autor;

@Service
public class AutorService {

	private List<Autor> autores = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();

	public List<AutorDto> listar() {

		return autores.stream().map(t -> modelMapper.map(t, AutorDto.class)).collect(Collectors.toList());
	}

	public void cadastrar(AutorFormDto dto) {

		Autor autor = new ModelMapper().map(dto, Autor.class);

		autores.add(autor);

	}

}
