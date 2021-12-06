package br.com.java.livraria.infra.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.java.livraria.model.Usuario;
import br.com.java.livraria.repository.UsuarioRepository;

public class TokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public TokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty()) {
			filterChain.doFilter(request, response);
			return;
		}

		token = token.replace("Bearer ", "");
		boolean tokenValido = tokenService.isValido(token);
		if (tokenValido) {
			Long idUsuario = tokenService.extrairIdUsuario(token);
			Usuario logado = usuarioRepository.carregarPorIdComPerfis(idUsuario).get();
			Authentication authentication = new UsernamePasswordAuthenticationToken(logado, null,
					logado.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}
}