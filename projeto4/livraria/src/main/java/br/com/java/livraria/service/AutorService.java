package br.com.java.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.java.livraria.dto.AutorDto;
import br.com.java.livraria.dto.AutorFormDto;
import br.com.java.livraria.model.Autor;
import br.com.java.livraria.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	private ModelMapper modelMapper = new ModelMapper();

	public Page<AutorDto> listar(Pageable paginacao) {
		Page<Autor> autores = autorRepository.findAll(paginacao);
		return autores.map(t -> modelMapper.map(t, AutorDto.class));
	}

	@Transactional
	public void cadastrar(AutorFormDto dto) {
		Autor autor = new ModelMapper().map(dto, Autor.class);
		autor.setId(null);
		autorRepository.save(autor);
	}
}
