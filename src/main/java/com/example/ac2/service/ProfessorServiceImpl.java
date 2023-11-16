package com.example.ac2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dto.ProfessorDTO;
import com.example.ac2.dto.UpdateListLongDTO;
import com.example.ac2.exceptions.RegraException;
import com.example.ac2.model.Curso;
import com.example.ac2.model.Professor;
import com.example.ac2.repository.CursoRepository;
import com.example.ac2.repository.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private ProfessorRepository professorRepository;

    private CursoRepository cursoRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, CursoRepository cursoRepository) {
        this.professorRepository = professorRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<ProfessorDTO> findAll() {
        return professorRepository.findAll().stream().map(
                (Professor professor) -> {
                    List<Long> cursosIds = professor.getCursoList().stream()
                            .map(curso -> curso.getId())
                            .collect(Collectors.toList());

                    return ProfessorDTO.builder()
                            .id(professor.getId())
                            .nome(professor.getNome())
                            .cpf(professor.getCpf())
                            .rg(professor.getRg())
                            .endereco(professor.getEndereco())
                            .celular(professor.getCelular())
                            .cursoList(cursosIds)
                            .build();
                })
                .collect(Collectors.toList());

    }

    @Override
    public void create(ProfessorDTO professor) {

        List<Curso> cursoList = cursoRepository.findAllById(professor.getCursoList());

        if (cursoList.size() != professor.getCursoList().size()) {
            throw new RegraException("Um ou mais cursos não foram encontrados dentro da lista.");
        }

        Professor professorSaved = Professor.builder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .rg(professor.getRg())
                .endereco(professor.getEndereco())
                .celular(professor.getCelular())
                .cursoList(cursoList)
                .build();

        professorRepository.save(professorSaved);
    }

    @Override
    public void update(Long id, UpdateListLongDTO cursoList) {
        Professor professorEncontrado = professorRepository.findById(id).orElseThrow(
                () -> new RegraException("Não foi possível encontrar o professor pelo ID fornecido."));

        List<Curso> cursoListFound = cursoRepository.findAllById(cursoList.getLongList());

        if (cursoListFound.size() != cursoList.getLongList().size()) {
            throw new RegraException("Um ou mais cursos não foram encontrados dentro da lista.");
        }

        professorEncontrado.setCursoList(cursoListFound);
        professorRepository.save(professorEncontrado);
    }

    @Override
    public Boolean login(String nome, String cpf) {
        Optional<Professor> professor = professorRepository.findByCpf(cpf);

        if (professor.isPresent()) {
            Professor professorEncontrado = professor.get();
            if (professorEncontrado.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
}
