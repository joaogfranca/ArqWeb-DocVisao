package com.example.ac2.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dto.AgendaDTO;
import com.example.ac2.exceptions.RegraException;
import com.example.ac2.model.Agenda;
import com.example.ac2.model.Curso;
import com.example.ac2.model.Professor;
import com.example.ac2.repository.AgendaRepository;
import com.example.ac2.repository.CursoRepository;
import com.example.ac2.repository.ProfessorRepository;

@Service
public class AgendaServiceImpl implements AgendaService {

        private AgendaRepository agendaRepository;

        private ProfessorRepository professorRepository;

        private CursoRepository cursoRepository;

        public AgendaServiceImpl(AgendaRepository agendaRepository, ProfessorRepository professorRepository,
                        CursoRepository cursoRepository) {
                this.agendaRepository = agendaRepository;
                this.professorRepository = professorRepository;
                this.cursoRepository = cursoRepository;
        }

        @Override
        public List<AgendaDTO> findAll() {
                return agendaRepository.findAll().stream().map(
                                (Agenda agenda) -> AgendaDTO.builder()
                                                .id(agenda.getId())
                                                .dataInicio(agenda.getDataInicio())
                                                .dataFim(agenda.getDataFim())
                                                .horarioInicio(agenda.getHorarioInicio())
                                                .horarioFim(agenda.getHorarioFim())
                                                .estado(agenda.getEstado())
                                                .cidade(agenda.getCidade())
                                                .cep(agenda.getCep())
                                                .cursoId(agenda.getCurso().getId())
                                                .professorId(agenda.getProfessor().getId())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public void create(AgendaDTO agenda) {
                Curso curso = cursoRepository.findById(agenda.getCursoId())
                                .orElseThrow(() -> new RegraException(
                                                "Não foi possível encontrar o curso pelo ID"));

                Professor professor = professorRepository.findById(agenda.getProfessorId())
                                .orElseThrow(() -> new RegraException(
                                                "Não foi possível encontrar o professor pelo ID"));

                boolean especializacao = curso.getProfessorList().stream()
                                .anyMatch(professorItem -> professorItem.getId().equals(professor.getId()));

                if (!especializacao) {
                        throw new RegraException(
                                        "O professor informado não possui a especialização para esse curso.");
                }

                List<Agenda> agendaConflito = agendaRepository.findConflictingAgendas(agenda.getProfessorId(),
                                agenda.getDataInicio(), agenda.getDataFim(), agenda.getId());

                if (!agendaConflito.isEmpty()) {
                        throw new RegraException(
                                        "O professor já está ministrando o mesmo curso em outro local na mesma data.");
                }

                Agenda agendaSaved = Agenda.builder()
                                .id(agenda.getId())
                                .dataInicio(agenda.getDataInicio())
                                .dataFim(agenda.getDataFim())
                                .horarioInicio(agenda.getHorarioInicio())
                                .horarioFim(agenda.getHorarioFim())
                                .estado(agenda.getEstado())
                                .cidade(agenda.getCidade())
                                .cep(agenda.getCep())
                                .curso(curso)
                                .professor(professor)
                                .build();

                agendaRepository.save(agendaSaved);
        }

        @Override
        public List<AgendaDTO> findByProfessor(String nomeProfessor) {
                Professor professor = professorRepository.findByNome(nomeProfessor)
                                .orElseThrow(() -> new RegraException(
                                                "Não foi possível encontrar o professor!"));

                return agendaRepository.findAgendaByProfessor(professor.getId()).stream().map(
                                (Agenda agenda) -> AgendaDTO.builder()
                                                .id(agenda.getId())
                                                .dataInicio(agenda.getDataInicio())
                                                .dataFim(agenda.getDataFim())
                                                .horarioInicio(agenda.getHorarioInicio())
                                                .horarioFim(agenda.getHorarioFim())
                                                .estado(agenda.getEstado())
                                                .cidade(agenda.getCidade())
                                                .cep(agenda.getCep())
                                                .cursoId(agenda.getCurso().getId())
                                                .professorId(agenda.getProfessor().getId())
                                                .build())
                                .collect(Collectors.toList());
        }
}
