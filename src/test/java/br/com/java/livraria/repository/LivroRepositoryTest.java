package br.com.java.livraria.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.java.livraria.dto.RelatorioLivrosDto;
import br.com.java.livraria.model.Autor;
import br.com.java.livraria.model.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Re)
@ActiveProfiles("test")
public class LivroRepositoryTest {

	@Autowired
	private LivroRepository repository;

	@Autowired
	private TestEntityManager em;

	@Test
	void deveriaRetornarRelatorioDeLivrosPorAutor() {
		Autor autor1 = new Autor("Autor 1", "autor1@gmail.com", LocalDate.now(), "Minicurrículo autor 1");
		em.persist(autor1);
		Autor autor2 = new Autor("Autor 2", "autor2@gmail.com", LocalDate.now(), "Minicurrículo autor 2");
		em.persist(autor2);

		Livro livro1 = new Livro("Título do livro 1", LocalDate.now(), 101, autor1);
		em.persist(livro1);
		Livro livro2 = new Livro("Título do livro 2", LocalDate.now(), 102, autor2);
		em.persist(livro2);


		List<RelatorioLivrosDto> relatorio = repository.relatorioQuantidadeDeLivros();
		Assertions.assertThat(relatorio).hasSize(2)
				.extracting(RelatorioLivrosDto::getAutor, RelatorioLivrosDto::getQuantidade,
						RelatorioLivrosDto::getPercentual)
				.containsExactlyInAnyOrder(Assertions.tuple("Autor 1", 1l, 0.5),
						Assertions.tuple("Autor 2", 1l, 0.5));
	}
}
