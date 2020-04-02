package com.hbsis.controle.escolar.turnos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private final ITurnoRepository iTurnoRepository;

    public TurnoService(ITurnoRepository iTurnoRepository) {
        this.iTurnoRepository = iTurnoRepository;
    }

    public TurnoDTO findById(Long id) {
        Optional<Turno> turnoOptional = this.iTurnoRepository.findById(id);

        if (turnoOptional.isPresent()) {
            return TurnoDTO.of(turnoOptional.get());
        }

        throw new IllegalArgumentException(String.format("Turno de ID [%s] não encontrado.", id));
    }

    public Optional<Turno> findByIdOptional(Long id) {
        Optional<Turno> turnoOptional = this.iTurnoRepository.findById(id);

        if (turnoOptional.isPresent()) {
            return turnoOptional;
        }

        throw new IllegalArgumentException(String.format("Turno de ID [%s] não encontrado.", id));
    }

    public List<Turno> findAll() {
        return iTurnoRepository.findAll();
    }
}
