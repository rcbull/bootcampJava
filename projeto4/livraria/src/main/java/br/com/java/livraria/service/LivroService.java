package br.com.java.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.java.livraria.dto.LivroAtualizarFormDto;
import br.com.java.livraria.dto.LivroDto;
import br.com.java.livraria.dto.LivroFormDto;
import br.com.java.livraria.model.Autor;
import br.com.java.livraria.model.Livro;
import br.com.java.livraria.repository.AutorRepository;
import br.com.java.livraria.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public Page<LivroDto> listar(Pageable paginacao) {
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(t -> modelMapper.map(t, LivroDto.class));
	}

	@Transactional
	public LivroDto cadastrar(LivroFormDto dto) {
		try {
			Livro livro = modelMapper.map(dto, Livro.class);
			Autor autor = autorRepository.getById(dto.getAutorId());

			livro.setId(null);
			livro.setAutor(autor);

			livroRepository.save(livro);
			return modelMapper.map(livro, LivroDto.class);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Autor inexistente!");
		}
	}

	@Transactional
	public LivroDto atualizar(@Valid LivroAtualizarFormDto dto) {
		Livro livro = livroRepository.getById(dto.getId());

		livro.atualizarInformacoes(dto.getTitulo(), dto.getDataLancamento(), dto.getNumeroDePaginas());
		return modelMapper.map(livro, LivroDto.class);
	}

	@Transactional
	public void deletar(@NotNull Long id) {
		livroRepository.deleteById(id);

	}

	public LivroDto consultar(@NotNull Long id) {
		Livro livro = livroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(livro, LivroDto.class);
	}
}
