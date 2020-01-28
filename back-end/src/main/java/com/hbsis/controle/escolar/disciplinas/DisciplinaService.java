package com.hbsis.controle.escolar.disciplinas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisciplinaService.class);
    private IDisciplinaRepository iDisciplinaRepository;

    public DisciplinaService(IDisciplinaRepository iDisciplinaRepository) {
        this.iDisciplinaRepository = iDisciplinaRepository;
    }

    public DisciplinaDTO save(DisciplinaDTO disciplinaDTO) {
        this.validarExistencia(disciplinaDTO.getNome());

        Disciplina disciplina = new Disciplina(
                disciplinaDTO.getNome()
        );

        disciplina = iDisciplinaRepository.save(disciplina);

        return DisciplinaDTO.of(disciplina);
    }

    public DisciplinaDTO update(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplinaNova = findById(disciplinaDTO.getId());

        disciplinaNova.setNome(disciplinaDTO.getNome());
        disciplinaNova = iDisciplinaRepository.save(disciplinaNova);

        return DisciplinaDTO.of(disciplinaNova);
    }

    public void delete(Long id){
        if(iDisciplinaRepository.existsById(id)){
            this.iDisciplinaRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException(String.format("Disciplina de ID [%s] não encontrada.", id));
        }
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

    private void validarExistencia(String nome) {
        if (iDisciplinaRepository.existsByNome(nome)) {
            throw new IllegalArgumentException("Disciplina já existente.");
        }
    }
}
