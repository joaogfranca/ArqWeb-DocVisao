package com.example.ac2.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Descrição não pode ser vazia")
    private String descricao;
    @Min(value = 1, message = "Carga horário não pode ser menor que 1 hora")
    private Integer cargaHoraria;
    @NotBlank(message = "Objetivo não pode ser vazia")
    private String objetivo;
    private String ementa;
    @ManyToMany
    private List<Professor> professorList;
}
