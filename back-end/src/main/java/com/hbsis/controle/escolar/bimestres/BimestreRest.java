package com.hbsis.controle.escolar.bimestres;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bimestres")
public class BimestreRest {
    private final BimestreService bimestreService;

    public BimestreRest(BimestreService bimestreService) {
        this.bimestreService = bimestreService;
    }

    @GetMapping("/{id}")
    public BimestreDTO findById(@PathVariable("id") Long id){
        return this.bimestreService.findById(id);
    }
}
