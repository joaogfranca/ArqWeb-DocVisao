package com.example.ac2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.ac2.dto.ProfessorDTO;
import com.example.ac2.dto.UpdateListLongDTO;
import com.example.ac2.service.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @GetMapping
    public List<ProfessorDTO> findAllProfessor(){
        return professorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfessor(@Valid @RequestBody ProfessorDTO professor){
        professorService.create(professor);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateListLongDTO cursoList){
        professorService.update(id, cursoList);
    }

    @PostMapping("/login")
    public Boolean login(@RequestParam("nome") String nome, @RequestParam("cpf") String cpf){
        return professorService.login(nome, cpf);
    }
}
