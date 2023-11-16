package com.example.ac2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dto.AgendaDTO;
import com.example.ac2.service.AgendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public List<AgendaDTO> findAllAgenda() {
        return agendaService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAgenda(@Valid @RequestBody AgendaDTO agenda) {
        agendaService.create(agenda);
    }

    @GetMapping("/professor")
    public List<AgendaDTO> findAllAgendaByProfessor(@RequestParam("nomeProfessor") String nomeProfessor) {
        return agendaService.findByProfessor(nomeProfessor);
    }
}
