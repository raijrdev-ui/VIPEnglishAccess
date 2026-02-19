package br.com.vipenglish.access.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios_admin") // Corrigi o erro de digitação de "usuarions"
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String funcao;
    private String telefone;
    private String senha;

    // Construtor Vazio (Obrigatório para o Hibernate)
    public Usuario() {}

    // Construtor Completo
    public Usuario(Long id, String nomeCompleto, String cpf, String funcao, String telefone, String senha) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.funcao = funcao;
        this.telefone = telefone;
        this.senha = senha;
    }

    // GETTERS E SETTERS MANUAIS (O IntelliJ vai reconhecer na hora)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getFuncao() { return funcao; }
    public void setFuncao(String funcao) { this.funcao = funcao; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}