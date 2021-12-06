package br.com.java.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.jayway.jsonpath.JsonPath;

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
public class LivroControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

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
	void naoDeveriaCadastrarLivroComDadosIncompletos() throws Exception {
		String json = "{}";
		mvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content(json).header("Authorization",
				"Bearer " + token)).andExpect(status().isBadRequest());
	}

	@Test
	void deveriaCadastrarLivroComDadosCompletos() throws Exception {
		String jsonAutor = "{\"nome\":\"Robert C. Martin\", \"email\":\"unclebob@gmail.com\", \"dataNascimento\":\"1952-12-05\",\"miniCurriculo\":\"clean code...\"}";

		String jsonAutorRetornado = "{\"nome\":\"Robert C. Martin\", \"email\":\"unclebob@gmail.com\", \"dataNascimento\":\"1952-12-05\",\"miniCurriculo\":\"clean code...\"}";

		MvcResult resultado = mvc
				.perform(post("/autores").contentType(MediaType.APPLICATION_JSON).content(jsonAutor)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(content().json(jsonAutorRetornado)).andReturn();

		System.out.println(resultado.getResponse().getContentAsString());

		Integer id = JsonPath.read(resultado.getResponse().getContentAsString(), "$.id");

		String jsonLivro = "{" + "\"titulo\": \"Código limpo: Habilidades práticas do Agile Software\","
				+ "\"dataLancamento\": \"2009-09-08\"," + "\"numeroDePaginas\": 524," + "\"autor_id\": " + id + "}";

		String jsonLivroRetornado = "{" + "\"titulo\": \"Código limpo: Habilidades práticas do Agile Software\","
				+ "\"dataLancamento\": \"2009-09-08\"," + "\"numeroDePaginas\": 524," + "\"autor\": {\"id\":" + id + "}}";

		mvc.perform(post("/livros").contentType(MediaType.APPLICATION_JSON).content(jsonLivro).header("Authorization",
				"Bearer " + token)).andExpect(status().isCreated()).andExpect(header().exists("Location"))
				.andExpect(content().json(jsonLivroRetornado));
	}
}
