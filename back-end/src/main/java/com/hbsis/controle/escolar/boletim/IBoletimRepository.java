package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.notas.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBoletimRepository extends JpaRepository<Boletim, Long> {
    List<Boletim> findByAluno_Id(Long id);
}
