package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.models.Autor;

public class AutorDao {

	private Connection conexao;

	public AutorDao(Connection conexao) {
		this.conexao = conexao;
	}

	public void salvar(Autor autor) {
		try {
			String sql = "insert into autores (nome, email, data_nascimento, resume) values(?,?,?,?)";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, autor.getNome());
			ps.setString(2, autor.getEmail());
			ps.setDate(3, Date.valueOf(autor.getData_nascimento()));
			ps.setString(4, autor.getResume());
			
			ps.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Autor> listar() {
		try {
			String sql = "select * from autores";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Autor> autores = new ArrayList<>();
			
			while (rs.next()) {
				Autor autor = new Autor(
						rs.getString("nome"), 
						rs.getString("email"), 
						rs.getDate("data_nascimento").toLocalDate(),
						rs.getString("resume"));
				
						autores.add(autor);
			}
			
			return autores;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}