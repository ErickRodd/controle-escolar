package com.hbsis.controle.escolar.turmas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITurmaRepository extends JpaRepository<Turma, Long> {
    Optional<Turma> findByAlunoList_Id(Long id);

    boolean existsByAlunoList_Id(Long id);
}
