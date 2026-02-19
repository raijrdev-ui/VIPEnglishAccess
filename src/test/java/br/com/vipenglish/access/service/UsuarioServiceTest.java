package br.com.vipenglish.access.service;

import br.com.vipenglish.access.model.Usuario;
import br.com.vipenglish.access.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository; // Simula o banco de dados

    @InjectMocks
    private UsuarioService service; // Injeta o mock acima dentro do service real

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    @DisplayName("Deve validar login com sucesso quando as credenciais estão corretas")
    void validarLoginSucesso() {
        // GIVEN (Dado que...)
        Usuario usuarioMock = new Usuario();
        usuarioMock.setCpf("123");
        usuarioMock.setSenha("senha123");

        when(repository.findByCpf("123")).thenReturn(Optional.of(usuarioMock));

        // WHEN (Quando eu executar...)
        boolean resultado = service.validarLogin("123", "senha123");

        // THEN (Então o resultado deve ser...)
        assertTrue(resultado);
        verify(repository, times(1)).findByCpf("123");
    }

    @Test
    @DisplayName("Deve falhar no login quando a senha estiver incorreta")
    void validarLoginSenhaIncorreta() {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setCpf("123");
        usuarioMock.setSenha("senhaCorreta");

        when(repository.findByCpf("123")).thenReturn(Optional.of(usuarioMock));

        boolean resultado = service.validarLogin("123", "senhaErrada");

        assertFalse(resultado);
    }
}