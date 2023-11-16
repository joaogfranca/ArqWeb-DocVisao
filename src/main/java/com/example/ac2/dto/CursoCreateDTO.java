package com.example.ac2.dto;

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
public class CursoCreateDTO {
    private Long id;
    private String descricao;
    private Integer cargaHoraria;
    private String objetivo;
    private String ementa;
}
