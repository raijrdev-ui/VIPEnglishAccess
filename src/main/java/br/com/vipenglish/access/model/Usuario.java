package br.com.vipenglish.access.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarions_admin")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;


    @Column(unique = true, nullable = false)
    private String cpf;

    private String funcao; //Professor,adm ou coordenador
    private String telefone;
    private String senha;
}