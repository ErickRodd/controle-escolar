package com.hbsis.controle.escolar.alunos;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/alunos")
public class AlunoRest {
    private final AlunoService alunoService;

    public AlunoRest(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/save")
    public AlunoDTO save(@Valid @RequestBody AlunoDTO alunoDTO) {
        return this.alunoService.save(alunoDTO);
    }

    @PutMapping("/update")
    public AlunoDTO update(@Valid @RequestBody AlunoDTO alunoDTO) {
        return this.alunoService.update(alunoDTO);
    }

    @GetMapping("/get/{id}")
    public AlunoDTO get(@PathVariable("id") Long id) {
        return this.alunoService.findById(id);
    }

    @GetMapping("/get-all")
    public List<Aluno> getAll() {
        return this.alunoService.findAll();
    }

    @GetMapping("/list-with-no-turma")
    public List<Aluno> listWithNoTurma() {
        return this.alunoService.findAllWithNoTurma();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.alunoService.delete(id);
    }
}
