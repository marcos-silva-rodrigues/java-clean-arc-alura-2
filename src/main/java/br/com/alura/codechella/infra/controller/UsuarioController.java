package br.com.alura.codechella.infra.controller;

import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.domain.Usuario;
import br.com.alura.codechella.infra.persistence.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuario criarUsuario;

    public UsuarioController(CriarUsuario criarUsuario) {
        this.criarUsuario = criarUsuario;
    }

    @PostMapping
    @Transactional
    public UsuarioDto cadastrarUsuario(@RequestBody @Valid UsuarioDto dto, UriComponentsBuilder uriBuilder) {
        var salvo = criarUsuario.cadastrarUsuario(new Usuario(dto.cpf(), dto.nome(), dto.nascimento(), dto.email()));

        return new UsuarioDto(
                salvo.getCpf(),
                salvo.getNome(),
                salvo.getNascimento(),
                salvo.getEmail()
        );
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

}
