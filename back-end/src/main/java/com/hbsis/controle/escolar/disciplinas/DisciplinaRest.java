package com.hbsis.controle.escolar.disciplinas;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/disciplinas")
public class DisciplinaRest {
    private final DisciplinaService disciplinaService;

    public DisciplinaRest(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping("/save")
    public DisciplinaDTO save(@Valid @RequestBody DisciplinaDTO disciplinaDTO){
        return disciplinaService.save(disciplinaDTO);
    }

    @PutMapping("/update")
    public DisciplinaDTO update(@Valid @RequestBody DisciplinaDTO disciplinaDTO){
        return disciplinaService.update(disciplinaDTO);
    }

    @GetMapping("/{id}")
    public DisciplinaDTO get(@PathVariable("id") Long id){
        return DisciplinaDTO.of(disciplinaService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.disciplinaService.delete(id);
    }

    @GetMapping("/list")
    public List<Disciplina> getAll(){
        return disciplinaService.findAll();
    }
}
