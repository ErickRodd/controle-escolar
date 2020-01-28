package com.hbsis.controle.escolar.alunos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface IAlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByCpf(String cpf);
}
