package com.hbsis.controle.escolar.disciplinas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisciplinaService.class);
    private final IDisciplinaRepository iDisciplinaRepository;

    public DisciplinaService(IDisciplinaRepository iDisciplinaRepository) {
        this.iDisciplinaRepository = iDisciplinaRepository;
    }

    public DisciplinaDTO save(DisciplinaDTO disciplinaDTO) {
        validarExistencia(disciplinaDTO.getNome());

        Disciplina novaD = new Disciplina(
                disciplinaDTO.getNome()
        );

        novaD = iDisciplinaRepository.save(novaD);

        return DisciplinaDTO.of(novaD);
    }

    public DisciplinaDTO update(DisciplinaDTO disciplinaDTO) {
        Disciplina existenteD = findById(disciplinaDTO.getId());

        existenteD.setNome(disciplinaDTO.getNome());

        existenteD = iDisciplinaRepository.save(existenteD);

        return DisciplinaDTO.of(existenteD);
    }

    public Disciplina findById(Long id) {
        Optional<Disciplina> disciplinaOptional = this.iDisciplinaRepository.findById(id);

        if (disciplinaOptional.isPresent()) {
            return disciplinaOptional.get();
        }
        throw new IllegalArgumentException("Disciplina não encontrada.");
    }

    public List<Disciplina> findAll() {
        return iDisciplinaRepository.findAll();
    }

    public void delete(Long id) {
        if (iDisciplinaRepository.existsById(id)) {
            this.iDisciplinaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Disciplina não existente.");
        }
    }

    private void validarExistencia(String nome) {
        if (iDisciplinaRepository.existsByNome(nome)) {
            throw new IllegalArgumentException("Disciplina já existente.");
        }
    }
}
