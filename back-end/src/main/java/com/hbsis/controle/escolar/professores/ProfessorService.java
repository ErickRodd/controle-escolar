package com.hbsis.controle.escolar.professores;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private IProfessorRepository iProfessorRepository;

    public ProfessorService(IProfessorRepository iProfessorRepository) {
        this.iProfessorRepository = iProfessorRepository;
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        this.validateExistencia(professorDTO.getCpf());
        this.validateExistenciaEmail(professorDTO.getEmail());

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
        Optional<Professor> professorExistente = this.iProfessorRepository.findById(professorDTO.getId());

        if (!professorDTO.getCpf().equals(professorExistente.get().getCpf())) {
            validateExistencia(professorDTO.getCpf());
        }

        if (!professorDTO.getEmail().equals(professorExistente.get().getEmail())) {
            validateExistenciaEmail(professorDTO.getEmail());
        }

        Professor professorNovo = professorExistente.get();
        professorNovo.setNome(professorDTO.getNome());
        professorNovo.setSobrenome(professorDTO.getSobrenome());
        professorNovo.setCpf(professorDTO.getCpf());
        professorNovo.setEmail(professorDTO.getEmail());
        professorNovo.setTelefone(professorDTO.getTelefone());

        professorNovo = this.iProfessorRepository.save(professorNovo);

        return ProfessorDTO.of(professorNovo);
    }

    public ProfessorDTO findById(Long id) {
        Optional<Professor> professorOptional = this.iProfessorRepository.findById(id);

        if (professorOptional.isPresent()) {
            return ProfessorDTO.of(professorOptional.get());
        }

        throw new IllegalArgumentException(String.format("Professor de ID [%s] não existe.", id));
    }

    public List<Professor> findAll() {
        return this.iProfessorRepository.findAll();
    }

    public void delete(Long id) {
        if (this.iProfessorRepository.existsById(id)) {

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

    private void validateExistenciaEmail(String email) {
        if (iProfessorRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
    }
}
