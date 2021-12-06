package br.com.java.livraria.controller;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.java.livraria.infra.security.TokenService;
import br.com.java.livraria.model.Perfil;
import br.com.java.livraria.model.Usuario;
import br.com.java.livraria.repository.PerfilRepository;
import br.com.java.livraria.repository.UsuarioRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AutorControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private String token;
	
	@BeforeEach
	public void gerarToken() {
		Usuario logado = new Usuario("admin", "admin", "admin");
		Perfil admin = perfilRepository.findById(1l).get();
		logado.adicionarPerfil(admin);
		usuarioRepository.save(logado);
		Authentication authentication = new UsernamePasswordAuthenticationToken(logado, logado.getLogin());
		this.token = tokenService.gerarToken(authentication);		
	}
	
	@Test
	public void naoDeveriaCadastrarAutorComDadosIncompletos() throws Exception {
		String json = "{}";

		mvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json).header("Authorization",
				"Bearer " + token)).andExpect(status().isBadRequest());
	}

	@Test
	public void DeveriaCadastrarAutorComDadosCompletos() throws Exception {
		String json = "{\"nome\":\"Robert C. Martin\"," + "\"email\":\"unclebob@gmail.com\","
				+ "\"dataNascimento\":\"1952-12-5\"," + "\"miniCurriculo\":\"clean code...\"}";
		String jsonRetornado = "{\"nome\":\"Robert C. Martin\"," + "\"email\":\"unclebob@gmail.com\","
				+ "\"dataNascimento\":\"1952-12-5\"," + "\"miniCurriculo\":\"clean code...\"}";

		mvc.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(json).header("Authorization",
				"Bearer " + token)).andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(content().json(jsonRetornado));

	}
}
