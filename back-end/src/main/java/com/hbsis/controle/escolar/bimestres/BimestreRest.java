package com.hbsis.controle.escolar.bimestres;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/bimestres")
public class BimestreRest {
    private final BimestreService bimestreService;

    public BimestreRest(BimestreService bimestreService) {
        this.bimestreService = bimestreService;
    }

    @GetMapping("/{id}")
    public BimestreDTO findById(@PathVariable("id") Long id) {
        return this.bimestreService.findById(id);
    }

    @GetMapping("/list")
    public List<Bimestre> list() {
        return bimestreService.list();
    }
}
