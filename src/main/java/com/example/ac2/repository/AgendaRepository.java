package com.example.ac2.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ac2.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Query("select cc from Agenda cc inner join fetch cc.professor c where c.id = :id")
    List<Agenda> findAgendaByProfessor(@Param("id") Long id);

    @Query("SELECT a FROM Agenda a WHERE a.professor.id = :professorId AND a.dataInicio <= :dataFim AND a.dataFim >= :dataInicio AND a.id <> :agendaId")
    List<Agenda> findConflictingAgendas(@Param("professorId") Long professorId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("agendaId") Long agendaId);
}
