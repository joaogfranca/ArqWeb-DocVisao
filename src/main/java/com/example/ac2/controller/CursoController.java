package com.example.ac2.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dto.CursoCreateDTO;
import com.example.ac2.dto.CursoDTO;
import com.example.ac2.dto.UpdateListLongDTO;
import com.example.ac2.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<CursoDTO> findAllCurso(){
        return cursoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCurso(@Valid @RequestBody CursoCreateDTO curso){
        cursoService.create(curso);
    }

    @PutMapping("{id}")
    public void updateProfessores(@PathVariable Long id, @Valid @RequestBody UpdateListLongDTO professorList){
        cursoService.updateProfessores(id, professorList);
    }
}
