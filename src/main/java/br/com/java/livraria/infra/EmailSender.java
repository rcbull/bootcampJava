package br.com.java.livraria.infra;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {

	@Async
	void enviarEmail(String destinatario, String assunto, String mensagem);

}
