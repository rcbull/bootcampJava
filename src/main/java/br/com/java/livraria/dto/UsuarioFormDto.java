package br.com.java.livraria.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFormDto {

	@NotBlank
	private String nome;
	@NotBlank
	private String login;
	@NotNull
	private Long perfilId;
	@NotBlank
	@Email
	private String email;

}
