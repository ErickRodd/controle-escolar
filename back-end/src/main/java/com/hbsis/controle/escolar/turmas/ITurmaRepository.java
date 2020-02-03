package com.hbsis.controle.escolar.turmas;

import com.hbsis.controle.escolar.alunos.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITurmaRepository extends JpaRepository<Turma, Long> {
    Optional<Turma> findByAlunoList_Id(Long id);
}
