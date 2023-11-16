package com.example.ac2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dto.CursoCreateDTO;
import com.example.ac2.dto.CursoDTO;
import com.example.ac2.dto.UpdateListLongDTO;
import com.example.ac2.exceptions.RegraException;
import com.example.ac2.model.Curso;
import com.example.ac2.model.Professor;
import com.example.ac2.repository.CursoRepository;
import com.example.ac2.repository.ProfessorRepository;

@Service
public class CursoServiceImpl implements CursoService {

    private CursoRepository cursoRepository;

    private ProfessorRepository professorRepository;

    public CursoServiceImpl(CursoRepository cursoRepository, ProfessorRepository professorRepository) {
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<CursoDTO> findAll() {
        return cursoRepository.findAll().stream().map(
                (Curso curso) -> {
                    List<Long> professorIds = curso.getProfessorList().stream()
                            .map(professor -> professor.getId())
                            .collect(Collectors.toList());

                    return CursoDTO.builder()
                            .id(curso.getId())
                            .descricao(curso.getDescricao())
                            .cargaHoraria(curso.getCargaHoraria())
                            .objetivo(curso.getObjetivo())
                            .ementa(curso.getEmenta())
                            .professorList(professorIds)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public void create(CursoCreateDTO curso) {

        Curso cursoSaved = Curso.builder()
                .id(curso.getId())
                .descricao(curso.getDescricao())
                .cargaHoraria(curso.getCargaHoraria())
                .objetivo(curso.getObjetivo())
                .ementa(curso.getEmenta())
                .build();

        cursoRepository.save(cursoSaved);
    }

    @Override
    public void updateProfessores(Long id, UpdateListLongDTO professorList) {

        Curso cursoEncontrado = cursoRepository.findById(id)
                .orElseThrow(() -> new RegraException("Não foi possível encontrar o ID do curso fornecido."));

        List<Professor> professorListFound = professorRepository.findAllById(professorList.getLongList());

        if (professorListFound.size() != professorList.getLongList().size()) {
            throw new RegraException("Um ou mais professores não foram encontrados dentro da lista.");
        }

        cursoEncontrado.setProfessorList(professorListFound);
        cursoRepository.save(cursoEncontrado);
    }
}
