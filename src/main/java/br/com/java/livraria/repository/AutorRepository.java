package br.com.java.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.java.livraria.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
