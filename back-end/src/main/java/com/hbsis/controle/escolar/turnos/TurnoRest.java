package com.hbsis.controle.escolar.turnos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turnos")
public class TurnoRest {
    private TurnoService turnoService;

    public TurnoRest(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/{id}")
    public TurnoDTO get(@PathVariable("id") Long id){
        return this.turnoService.get(id);
    }
}
