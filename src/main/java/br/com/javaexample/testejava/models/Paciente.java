package br.com.javaexample.testejava.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "telefone", nullable = false, length = 15)
    private String telefone;

    // Construtores, getters e setters
}
