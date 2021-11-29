package br.com.java.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.java.livraria.dto.RelatorioLivrosDto;
import br.com.java.livraria.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	@Query("select new br.com.java.livraria.dto.RelatorioLivrosDto("
			+ "a.nome, "
			+ "count(1), "
			+ "count(1) * 1.0 / ((select count(1) from Livro) * 1.0)) "
			+ "from Livro l "
			+ "join Autor a "
			+ "on l.autor = a.id "
			+ "group by a.nome")
	List<RelatorioLivrosDto> relatorioQuantidadeDeLivros();
}
