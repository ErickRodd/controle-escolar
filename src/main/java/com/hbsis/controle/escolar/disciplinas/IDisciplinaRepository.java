package com.hbsis.controle.escolar.disciplinas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDisciplinaRepository extends JpaRepository<Disciplina, Long> {
    boolean existsByNome(String nome);
}
