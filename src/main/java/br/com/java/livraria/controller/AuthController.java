package br.com.java.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.java.livraria.dto.LoginFormDto;
import br.com.java.livraria.dto.TokenDto;
import br.com.java.livraria.infra.security.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping
	public TokenDto autenticar(@RequestBody @Valid LoginFormDto dto) {
		return new TokenDto(authService.autenticar(dto));

	}

}
