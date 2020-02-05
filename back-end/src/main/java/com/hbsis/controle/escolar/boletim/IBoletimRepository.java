package com.hbsis.controle.escolar.boletim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBoletimRepository extends JpaRepository<Boletim, Long> {
    List<Boletim> findByAluno_IdAndBimestre_Id(Long alunoId, Long bimestreId);
    boolean existsByAluno_Id(Long alunoId);
    boolean existsByAluno_IdAndBimestre_Id(Long alunoId, Long bimestre);
}
