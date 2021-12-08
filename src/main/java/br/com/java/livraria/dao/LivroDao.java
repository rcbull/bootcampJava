package br.com.java.livraria.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.java.livraria.model.Livro;

@Repository
public class LivroDao {

	@Autowired(required = false)
	private EntityManager em;

	public void salvar(Livro livro) {
		em.persist(livro);
	}

	public List<Livro> listar() {
		return em.createQuery("select l from Livros l", Livro.class).getResultList();
	}

}
