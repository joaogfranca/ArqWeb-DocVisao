package com.example.ac2.service;

import java.util.List;

import com.example.ac2.dto.CursoCreateDTO;
import com.example.ac2.dto.CursoDTO;
import com.example.ac2.dto.UpdateListLongDTO;

public interface CursoService {
    List<CursoDTO> findAll();

    void create(CursoCreateDTO curso);

    void updateProfessores(Long id, UpdateListLongDTO curso);
}
