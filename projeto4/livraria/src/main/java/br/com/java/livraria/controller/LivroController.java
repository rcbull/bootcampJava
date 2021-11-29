package br.com.java.livraria.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.java.livraria.dto.LivroAtualizarFormDto;
import br.com.java.livraria.dto.LivroDto;
import br.com.java.livraria.dto.LivroFormDto;
import br.com.java.livraria.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService service;

	@GetMapping
	public Page<LivroDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
		return service.listar(paginacao);
	}

	@PostMapping
	public void cadastrar(@RequestBody @Valid LivroFormDto dto) {
		service.cadastrar(dto);
	}

	@PutMapping
	public ResponseEntity<LivroDto> atualizar(@RequestBody @Valid LivroAtualizarFormDto dto) {
		LivroDto atualizado = service.atualizar(dto);

		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<LivroDto> remover(@PathVariable @NotNull Long id) {
		service.deletar(id);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> detalhar(@PathVariable @NotNull Long id) {
		LivroDto dto = service.consultar(id);

		return ResponseEntity.ok(dto);
	}
}
