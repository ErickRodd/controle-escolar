package com.hbsis.controle.escolar.turnos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurnoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final ITurnoRepository iTurnoRepository;

    public TurnoService(ITurnoRepository iTurnoRepository) {
        this.iTurnoRepository = iTurnoRepository;
    }

    public Optional<Turno> getOptional(Long id){
        Optional<Turno> turnoOptional = this.iTurnoRepository.findById(id);

        if(turnoOptional.isPresent()){
            return turnoOptional;
        }

        throw new IllegalArgumentException(String.format("Turno de ID [%s] não encontrado.", id));
    }

    public TurnoDTO get(Long id){
        Optional<Turno> turnoOptional = this.iTurnoRepository.findById(id);

        if(turnoOptional.isPresent()){
            return TurnoDTO.of(turnoOptional.get());
        }

        throw new IllegalArgumentException(String.format("Turno de ID [%s] não encontrado.", id));
    }
}
