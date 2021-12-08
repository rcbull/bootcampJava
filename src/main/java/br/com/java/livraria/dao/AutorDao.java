package br.com.java.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.java.livraria.model.Autor;

@Repository
public class AutorDao {

	@Autowired
	private EntityManager em;

	public void salvar(Autor autor) {
		em.persist(autor);
	}

	public List<Autor> listar() {
		return em.createQuery("select a from Autores a", Autor.class).getResultList();
	}

}