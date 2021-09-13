package br.com.projeto.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projeto.dao.AutorDao;
import br.com.projeto.factory.ConnectionFactory;
import br.com.projeto.models.Autor;

/**
 * Servlet implementation class AutorServlet
 */
@WebServlet("/autores")
public class AutorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AutorDao dao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutorServlet() {
    	Connection conexao = new ConnectionFactory().getConnection();
		this.dao = new AutorDao(conexao);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("autores", this.dao.listar());
		req.getRequestDispatcher("jsp/autores.jsp").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		LocalDate data_nascimento = LocalDate.parse(req.getParameter("data_nascimento"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String resume = req.getParameter("resume");
		
		Autor novo_autor = new Autor(nome, email, data_nascimento, resume);
		dao.salvar(novo_autor);
		res.sendRedirect("autores");
	}

}
