package com.hbsis.controle.escolar.professores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessorService.class);
    private IProfessorRepository iProfessorRepository;

    public ProfessorService(IProfessorRepository iProfessorRepository) {
        this.iProfessorRepository = iProfessorRepository;
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        this.validateExistencia(professorDTO.getCpf());

        LOGGER.info("Cadastrando novo professor...");

        Professor professor = new Professor(
                professorDTO.getNome(),
                professorDTO.getSobrenome(),
                professorDTO.getCpf(),
                professorDTO.getEmail(),
                professorDTO.getTelefone()
        );

        professor = this.iProfessorRepository.save(professor);

        return ProfessorDTO.of(professor);
    }

    public ProfessorDTO update(ProfessorDTO professorDTO) {
        LOGGER.info("Atualizando professor de ID [{}]", professorDTO.getId());

        Optional<Professor> professorExistente = this.iProfessorRepository.findById(professorDTO.getId());

        Professor professorNovo = professorExistente.get();
        professorNovo.setNome(professorDTO.getNome());
        professorNovo.setSobrenome(professorDTO.getSobrenome());
        professorNovo.setCpf(professorDTO.getCpf());
        professorNovo.setEmail(professorDTO.getEmail());
        professorNovo.setTelefone(professorDTO.getTelefone());

        professorNovo = this.iProfessorRepository.save(professorNovo);

        return ProfessorDTO.of(professorNovo);
    }

    public ProfessorDTO get(Long id) {
        Optional<Professor> professorOptional = this.iProfessorRepository.findById(id);

        if (professorOptional.isPresent()) {
            return ProfessorDTO.of(professorOptional.get());
        }

        throw new IllegalArgumentException(String.format("Professor de ID [%s] não existe.", id));
    }

    public List<Professor> getAll() {
        return this.iProfessorRepository.findAll();
    }

    public void delete(Long id) {
        if (this.iProfessorRepository.existsById(id)) {
            LOGGER.info("Excluindo professor de ID [{}]", id);

            this.iProfessorRepository.deleteById(id);
        } else {

            throw new IllegalArgumentException(String.format("Professor com ID [%s] não existe.", id));
        }
    }

    private void validateExistencia(String cpf) {
        if (this.iProfessorRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
    }
}
