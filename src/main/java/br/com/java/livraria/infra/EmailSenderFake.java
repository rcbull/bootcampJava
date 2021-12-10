package br.com.java.livraria.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile({ "default", "test" })
public class EmailSenderFake implements EmailSender {

	@Override
	@Async
	public void enviarEmail(String destinatario, String assunto, String mensagem) {

		System.out.println("----- FAKE DO ENVIO DE EMAIL -----");
		System.out.println("Destinatário: " + destinatario);
		System.out.println("Assunto: " + assunto);
		System.out.println("Mensagem: " + mensagem);
	}
}
