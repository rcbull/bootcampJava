package br.com.java.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.java.livraria.dto.AutorDto;
import br.com.java.livraria.dto.AutorFormDto;
import br.com.java.livraria.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {

	@Mock
	private AutorRepository autorRepository;

	@InjectMocks
	private AutorService autorService;

	@Test
	void cadastrarUmAutor() {
		AutorFormDto autorFormDto = new AutorFormDto("Aqui vai o autor", "autor@gmail.com", LocalDate.of(2000, 1, 1),
				"Aqui vai o mini currículo...");

		AutorDto dto = autorService.cadastrar(autorFormDto);

		Mockito.verify(autorRepository).save(Mockito.any());

		assertEquals(autorFormDto.getNome(), dto.getNome());
		assertEquals(autorFormDto.getEmail(), dto.getEmail());
		assertEquals(autorFormDto.getMiniCurriculo(), dto.getMiniCurriculo());
	}
}
