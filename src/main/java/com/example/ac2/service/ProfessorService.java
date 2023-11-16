package com.example.ac2.service;

import java.util.List;
import com.example.ac2.dto.ProfessorDTO;
import com.example.ac2.dto.UpdateListLongDTO;

public interface ProfessorService {
    List<ProfessorDTO> findAll();

    void create(ProfessorDTO professor);

    void update(Long id, UpdateListLongDTO cursoList);

    Boolean login(String nome, String cpf);
}
