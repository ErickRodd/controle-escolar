package com.hbsis.controle.escolar.turnos;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/list")
    public List<Turno> listar(){
        return turnoService.listar();
    }
}
