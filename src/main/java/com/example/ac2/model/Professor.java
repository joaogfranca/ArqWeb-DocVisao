package com.example.ac2.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;
    @NotBlank(message = "CPF não pode ser vazio")
    private String cpf;
    @NotBlank(message = "RG não pode ser vazio")
    private String rg;
    @NotBlank(message = "Endereço não pode ser vazio")
    private String endereco;
    @NotBlank(message = "Celular não pode ser vazio")
    private String celular;
    @ManyToMany
    @NotNull(message = "Lista de curso não pode ser vazia")
    private List<Curso> cursoList;
}
