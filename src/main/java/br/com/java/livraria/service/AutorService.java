package br.com.java.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.java.livraria.dto.AutorAtualizarFormDto;
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
	public AutorDto cadastrar(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);

		autorRepository.save(autor);
		return modelMapper.map(autor, AutorDto.class);
	}

	@Transactional
	public AutorDto atualizar(@Valid AutorAtualizarFormDto dto) {
		Autor autor = autorRepository.getById(dto.getId());

		autor.Atualizar(dto.getNome(), dto.getEmail(), dto.getDataNascimento(), dto.getMiniCurriculo());

		return modelMapper.map(autor, AutorDto.class);
	}

	@Transactional
	public void deletar(@NotNull Long id) {
		autorRepository.deleteById(id);

	}

	public AutorDto detalhar(@NotNull Long id) {
		Autor autor = autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(autor, AutorDto.class);
	}

	public AutorDto consultar(@NotNull Long id) {
		Autor autor = autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(autor, AutorDto.class);
	}
}
