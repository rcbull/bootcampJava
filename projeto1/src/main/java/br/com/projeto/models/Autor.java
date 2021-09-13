package br.com.projeto.models;

import java.time.LocalDate;

public class Autor {

	private String nome;
	private LocalDate data_nascimento;
	private String email;
	private String resume;	

	public Autor() {
	}

	public Autor(String nome, String email, LocalDate data_nascimento, String resume) {
		this.nome = nome;
		this.email = email;
		this.data_nascimento = data_nascimento;
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "Transacao [nome=" + nome + ", email=" + email + ", data_nascimento=" + data_nascimento + ", resume=" + resume + "]";
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public LocalDate getData_nascimento() {
		return data_nascimento;
	}


	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getResume() {
		return resume;
	}


	public void setResume(String resume) {
		this.resume = resume;
	}

	

}