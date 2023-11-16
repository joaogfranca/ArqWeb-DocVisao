package com.example.ac2.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Data do início não pode ser nula")
    private LocalDate dataInicio;
    @NotNull(message = "Data do fim não pode ser nula")
    private LocalDate dataFim;
    @Min(value = 1, message = "Horário de ínicio deve ser maior ou igual a 1 hora")
    private Integer horarioInicio;
    @Max(value = 24, message = "Carga horária não pode ultrapassar 24 horas")
    private Integer horarioFim;
    @NotBlank(message = "Estado não pode ser vazio")
    private String estado;
    @NotBlank(message = "Cidade não pode ser vazio")
    private String cidade;
    @NotBlank(message = "Cep não pode ser vazio")
    private String cep;
    @ManyToOne
    @NotNull(message = "Curso não pode ser vazio")
    private Curso curso;
    @ManyToOne
    @NotNull(message = "Professor não pode ser vazio")
    private Professor professor;
}
