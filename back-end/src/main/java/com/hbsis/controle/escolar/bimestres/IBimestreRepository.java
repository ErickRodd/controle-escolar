package com.hbsis.controle.escolar.bimestres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBimestreRepository extends JpaRepository<Bimestre, Long> {
}
