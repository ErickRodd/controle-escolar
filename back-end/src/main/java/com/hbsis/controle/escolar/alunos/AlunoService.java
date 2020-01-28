package com.hbsis.controle.escolar.alunos;

import com.hbsis.controle.escolar.turmas.Turma;
import com.hbsis.controle.escolar.turmas.TurmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoService.class);
    private final IAlunoRepository iAlunoRepository;
    private final TurmaService turmaService;

    public AlunoService(IAlunoRepository iAlunoRepository, TurmaService turmaService) {
        this.iAlunoRepository = iAlunoRepository;
        this.turmaService = turmaService;
    }

    public AlunoDTO save(AlunoDTO alunoDTO) {
        this.validateExistencia(alunoDTO.getCpf());

        LOGGER.info("\n");
        LOGGER.info("Cadastrando novo aluno...");

        Aluno aluno = new Aluno(
                alunoDTO.getNome(),
                alunoDTO.getCpf(),
                alunoDTO.getEmail(),
                alunoDTO.getTelefone(),
                findTurma(alunoDTO.getTurmaId())
        );

        aluno = this.iAlunoRepository.save(aluno);

        LOGGER.info("Aluno cadastrado com sucesso.\n");

        return AlunoDTO.of(aluno);
    }

    public AlunoDTO update(AlunoDTO alunoDTO) {
        LOGGER.info("\n");
        LOGGER.info("Atualizando aluno de ID [{}]...", alunoDTO.getId());

        Aluno alunoNovo = findAluno(alunoDTO.getId());
        alunoNovo.setNome(alunoDTO.getNome());
        alunoNovo.setCpf(alunoDTO.getCpf());
        alunoNovo.setEmail(alunoDTO.getEmail());
        alunoNovo.setTelefone(alunoDTO.getTelefone());
        alunoNovo.setTurma(findTurma(alunoDTO.getTurmaId()));

        alunoNovo = this.iAlunoRepository.save(alunoNovo);

        LOGGER.info("Aluno atualizado com sucesso.\n");

        return AlunoDTO.of(alunoNovo);
    }

    public AlunoDTO get(Long id) {
        LOGGER.info("Procurando por aluno de ID [{}]", id);

        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return AlunoDTO.of(alunoOptional.get());
        }

        throw new IllegalArgumentException(String.format("Aluno de ID [%s] não existe.", id));
    }

    public Optional<Aluno> getOptional(Long id) {
        LOGGER.info("Procurand por aluno de ID [{}]", id);

        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return alunoOptional;
        }

        throw new IllegalArgumentException(String.format("Aluno de ID [%s] não existe.", id));
    }

    public List<Aluno> getAll() {
        LOGGER.info("Listando todos os alunos...");

        return this.iAlunoRepository.findAll();
    }

    public void delete(Long id) {
        if (this.iAlunoRepository.existsById(id)) {
            LOGGER.info("Excluindo aluno de ID [{}]", id);

            this.iAlunoRepository.deleteById(id);
        } else {

            throw new IllegalArgumentException(String.format("Aluno de ID [%s] não existe.", id));
        }
    }

    private void validateExistencia(String cpf) {
        if (this.iAlunoRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
    }

    private Aluno findAluno(Long id) {
        return iAlunoRepository.findById(id).get();
    }

    private Turma findTurma(Long id) {
        return turmaService.get(id).get();
    }
}
