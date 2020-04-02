package com.hbsis.controle.escolar.professores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
