package br.com.vipenglish.access.repository;

import br.com.vipenglish.access.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Cost. Spring cria Logica pelo nome.
    Optional<Usuario> findByCpf(String cpf);
}
