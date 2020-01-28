package com.hbsis.controle.escolar.boletim;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/boletins")
public class BoletimRest {
    private final BoletimService boletimService;

    public BoletimRest(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    @PostMapping("/save")
    public BoletimDTO save(@Valid @RequestBody BoletimDTO boletimDTO){
        return this.boletimService.save(boletimDTO);
    }

    @PutMapping("/update")
    public BoletimDTO update(@Valid @RequestBody BoletimDTO boletimDTO){
        return this.boletimService.update(boletimDTO);
    }

    @GetMapping("/{id}")
    public BoletimDTO find(@PathVariable("id") Long id){
        return this.boletimService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        this.boletimService.delete(id);
    }
}
