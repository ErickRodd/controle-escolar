package com.hbsis.controle.escolar.turmas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/turmas")
public class TurmaRest {
    private final TurmaService turmaService;

    @Autowired
    public TurmaRest(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping("/save")
    public TurmaDTO save(@Valid @RequestBody TurmaDTO turmaDTO) {
        return turmaService.save(turmaDTO);
    }

    @PutMapping("/update")
    public TurmaDTO update(@Valid @RequestBody TurmaDTO turmaDTO) {
        return turmaService.update(turmaDTO);
    }

    @GetMapping("/{id}")
    public TurmaDTO findById(@PathVariable("id") Long id) {
        return turmaService.findById(id);
    }

    @GetMapping("/list")
    public List<Turma> list() {
        return turmaService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        turmaService.delete(id);
    }
}
