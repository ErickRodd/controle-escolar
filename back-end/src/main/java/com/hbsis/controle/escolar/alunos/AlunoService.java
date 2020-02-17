package com.hbsis.controle.escolar.alunos;

import com.hbsis.controle.escolar.turmas.TurmaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    private final IAlunoRepository iAlunoRepository;
    private final TurmaService turmaService;

    public AlunoService(IAlunoRepository iAlunoRepository, TurmaService turmaService) {
        this.iAlunoRepository = iAlunoRepository;
        this.turmaService = turmaService;
    }

    public AlunoDTO save(AlunoDTO alunoDTO) {
        this.validateExistencia(alunoDTO.getCpf());
        this.validateExistenciaEmail(alunoDTO.getEmail());

        Aluno aluno = new Aluno(
                alunoDTO.getNome(),
                alunoDTO.getSobrenome(),
                alunoDTO.getCpf(),
                alunoDTO.getEmail(),
                alunoDTO.getTelefone()
        );

        aluno = this.iAlunoRepository.save(aluno);

        return AlunoDTO.of(aluno);
    }

    public AlunoDTO update(AlunoDTO alunoDTO) {

        Aluno aluno = findAluno(alunoDTO.getId());

        if (!alunoDTO.getCpf().equals(aluno.getCpf())) {
            validateExistencia(alunoDTO.getCpf());
        }

        if (!alunoDTO.getEmail().equals(aluno.getEmail())) {
            validateExistenciaEmail(alunoDTO.getEmail());
        }

        aluno.setNome(alunoDTO.getNome());
        aluno.setSobrenome(alunoDTO.getSobrenome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setTelefone(alunoDTO.getTelefone());

        aluno = this.iAlunoRepository.save(aluno);

        return AlunoDTO.of(aluno);
    }

    public AlunoDTO findById(Long id) {
        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return AlunoDTO.of(alunoOptional.get());
        }

        throw new IllegalArgumentException(String.format("Aluno de ID [%s] não existe.", id));
    }

    public Optional<Aluno> findByIdOptional(Long id) {
        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return alunoOptional;
        }

        throw new IllegalArgumentException(String.format("Aluno de ID [%s] não existe.", id));
    }

    public List<Aluno> findAll() {
        return this.iAlunoRepository.findAll();
    }

    public List<Aluno> findAllWithNoTurma() {
        List<Aluno> alunoList = iAlunoRepository.findAll();

        alunoList.removeIf(aluno -> turmaService.existsByAlunoId(aluno.getId()));

        return alunoList;
    }

    public void delete(Long id) {
        if (this.iAlunoRepository.existsById(id)) {

            turmaService.deleteAluno(id);
            iAlunoRepository.deleteById(id);
        } else {

            throw new IllegalArgumentException(String.format("Aluno de ID [%s] não existe.", id));
        }
    }

    private void validateExistencia(String cpf) {
        if (this.iAlunoRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
    }

    private void validateExistenciaEmail(String email) {
        if (iAlunoRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
    }

    private Aluno findAluno(Long id) {
        return iAlunoRepository.findById(id).get();
    }
}
