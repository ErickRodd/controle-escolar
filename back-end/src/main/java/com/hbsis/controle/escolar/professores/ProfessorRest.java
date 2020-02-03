package com.hbsis.controle.escolar.professores;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/professor")
public class ProfessorRest {
    private final ProfessorService professorService;

    public ProfessorRest(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/save")
    public ProfessorDTO save(@Valid @RequestBody ProfessorDTO professorDTO) {
        return this.professorService.save(professorDTO);
    }

    @PutMapping("/update")
    public ProfessorDTO update(@Valid @RequestBody ProfessorDTO professorDTO) {
        return this.professorService.update(professorDTO);
    }

    @GetMapping("/get/{id}")
    public ProfessorDTO get(@PathVariable("id") Long id) {
        return this.professorService.get(id);
    }

    @GetMapping("/get-all")
    public List<Professor> getAll() {
        return this.professorService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.professorService.delete(id);
    }
}
