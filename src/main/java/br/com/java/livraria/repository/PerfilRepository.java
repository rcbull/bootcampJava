package br.com.java.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.java.livraria.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
