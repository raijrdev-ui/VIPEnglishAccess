package br.com.vipenglish.access.service;


import br.com.vipenglish.access.model.Usuario;
import br.com.vipenglish.access.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService{

     private final UsuarioRepository repository;

     public UsuarioService(UsuarioRepository repository){
         this.repository = repository;
     }


     // logica para Form de primeiro acesso
    public String criarPrimeiroAcesso(Usuario novoAdmin){
         Optional<Usuario> existente = repository.findByCpf(novoAdmin.getCpf());

         if (existente.isPresent()){
             return "Erro: Este CPF já está cadastrado como Administrador.";
         }

         repository.save(novoAdmin);
         return "Sucesso: Admin "+ novoAdmin.getNomeCompleto() + "cadastrado com sucesso!";
    }
    //Validação de login

    public boolean validarLogin(String cpf, String senha){
        return repository.findByCpf((cpf))
                 .map(user -> user.getSenha().equals(senha))
                 .orElse(false);
    }
}