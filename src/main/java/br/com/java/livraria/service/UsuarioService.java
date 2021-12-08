package br.com.java.livraria.service;

import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.java.livraria.dto.UsuarioDto;
import br.com.java.livraria.dto.UsuarioFormDto;
import br.com.java.livraria.model.Perfil;
import br.com.java.livraria.model.Usuario;
import br.com.java.livraria.repository.PerfilRepository;
import br.com.java.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Page<UsuarioDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t, UsuarioDto.class));

	}

	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto) {

		Usuario usuario = modelMapper.map(dto, Usuario.class);

		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		usuario.adicionarPerfil(perfil);

		String senha = new Random().nextInt(999999) + "";
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));

		usuarioRepository.save(usuario);

		return modelMapper.map(usuario, UsuarioDto.class);

	}
}
