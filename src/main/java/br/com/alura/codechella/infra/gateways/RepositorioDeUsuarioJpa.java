package br.com.alura.codechella.infra.gateways;

import br.com.alura.codechella.application.gateways.RepositorioDeUsuario;
import br.com.alura.codechella.domain.Usuario;
import br.com.alura.codechella.infra.persistence.UsuarioRepository;

import java.util.List;


public class RepositorioDeUsuarioJpa implements RepositorioDeUsuario {

  private final UsuarioRepository repository;
  private final UsuarioEntityMapper mapper;

  public RepositorioDeUsuarioJpa(UsuarioRepository repository, UsuarioEntityMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Usuario cadastrarUsuario(Usuario usuario) {
    var entity = repository.save(mapper.toEntity(usuario));
    return mapper.toDomain(entity);
  }

  @Override
  public List<Usuario> listarTodos() {
    return repository.findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
  }
}
