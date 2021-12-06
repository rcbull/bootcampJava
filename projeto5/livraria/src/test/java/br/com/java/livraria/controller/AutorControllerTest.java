package br.com.java.livraria.controller;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AutorControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void naoDeveriaCadastrarAutorComDadosIncompletos() throws Exception {
		String json = "{}";

		mvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
	}

	@Test
	public void DeveriaCadastrarAutorComDadosCompletos() throws Exception {
		String json = "{\"nome\":\"Robert C. Martin\"," + "\"email\":\"unclebob@gmail.com\","
				+ "\"dataNascimento\":\"1952-12-5\"," + "\"miniCurriculo\":\"clean code...\"}";
		String jsonRetornado = "{\"nome\":\"Robert C. Martin\"," + "\"email\":\"unclebob@gmail.com\","
				+ "\"dataNascimento\":\"1952-12-5\"," + "\"miniCurriculo\":\"clean code...\"}";

		mvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(content().json(jsonRetornado));

	}
}
