package br.com.java.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.java.livraria.dto.LivroDto;
import br.com.java.livraria.dto.LivroFormDto;
import br.com.java.livraria.repository.AutorRepository;
import br.com.java.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

	@Mock
	private AutorRepository autorRepository;

	@Mock
	private LivroRepository livroRepository;

	@InjectMocks
	private LivroService livroService;

	@Test
	void CadastrarUmLivro() {
		LivroFormDto livroFormDto = new LivroFormDto("Aqui vai o título do livro", LocalDate.of(2000, 1, 1), 111, 1l);

		LivroDto dto = livroService.cadastrar(livroFormDto);

		Mockito.verify(livroRepository).save(Mockito.any());

		assertEquals(livroFormDto.getTitulo(), dto.getTitulo());
		assertEquals(livroFormDto.getDataLancamento(), dto.getDataLancamento());
		assertEquals(livroFormDto.getNumeroDePaginas(), dto.getNumeroDePaginas());

	}

	@Test
	void CadastrarUmLivroComAutorInvalido() {
		LivroFormDto livroFormDto = new LivroFormDto("Deve dar erro", LocalDate.of(2000, 1, 1), 100, 99999l);

		Mockito.when(autorRepository.getById(livroFormDto.getAutorId())).thenThrow(EntityNotFoundException.class);

		assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar(livroFormDto));
	}
}
