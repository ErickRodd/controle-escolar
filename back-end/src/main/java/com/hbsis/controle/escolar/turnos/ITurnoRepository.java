package com.hbsis.controle.escolar.turnos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
