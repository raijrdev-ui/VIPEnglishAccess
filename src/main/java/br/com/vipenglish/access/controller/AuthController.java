package br.com.vipenglish.access.controller;


import br.com.vipenglish.access.model.Usuario;
import br.com.vipenglish.access.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService service;

    public AuthController(UsuarioService service){
        this.service =service;
    }

    //Rota para form de cadastro (Quando primeiro acesso)
    @PostMapping("/registrar")
    public String registrar(@RequestBody Usuario usuario){
        return service.criarPrimeiroAcesso(usuario);
    }
    // Rota para a tela de login
    @PostMapping("/login")
    public String login (@RequestParam String cpf, @RequestParam String senha){
        boolean logado = service.validarLogin(cpf, senha);
        return logado ? "Acesso concedido ao VIP Panel": "CPF ou Senha inválidos";
    }
}
