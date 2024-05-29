package br.com.alura.codechella.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioEntityTest {

  @Test
  public void naoDeveCadastrarUsuarioComCpfInvalido() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Usuario("123546.789-01", "jacque", LocalDate.parse("1990-09-08"), "email@email.com");
    });
  }

  @Test
  public void deveCriarUsuarioFabricaDeUsuario() {
    FabricaDeUsuario fab = new FabricaDeUsuario();
    var usuario = fab.comNomeCpfNascimento("Emily", "123.123.123-09",
            LocalDate.parse("2001-10-01"));

    assertEquals(usuario.getNome(), "Emily");

    usuario = fab.incluiEndereco("12345-122", 40, "apto 123");

    assertEquals(usuario.getEndereco().getComplemento(), "apto 123");
  }
}
