package com.hbsis.controle.escolar.alunos;

import com.hbsis.controle.escolar.turmas.Turma;
import com.hbsis.controle.escolar.turmas.TurmaService;
import org.apache.commons.lang.StringUtils;
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
        this.validateExistenciaEmail(alunoDTO.getEmail());

        LOGGER.info("\n");
        LOGGER.info("Cadastrando novo aluno...");

        Aluno aluno = new Aluno(
                alunoDTO.getNome(),
                alunoDTO.getSobrenome(),
                alunoDTO.getCpf(),
                alunoDTO.getEmail(),
                alunoDTO.getTelefone()
        );

        aluno = this.iAlunoRepository.save(aluno);

        LOGGER.info("Aluno cadastrado com sucesso.\n");

        return AlunoDTO.of(aluno);
    }

    public AlunoDTO update(AlunoDTO alunoDTO) {
        LOGGER.info("\n");
        LOGGER.info("Atualizando aluno de ID [{}]...", alunoDTO.getId());

        Aluno aluno = findAluno(alunoDTO.getId());

        if(!alunoDTO.getCpf().equals(aluno.getCpf())){
            validateExistencia(alunoDTO.getCpf());
        }

        if(!alunoDTO.getEmail().equals(aluno.getEmail())){
            validateExistenciaEmail(alunoDTO.getEmail());
        }

        aluno.setNome(alunoDTO.getNome());
        aluno.setSobrenome(alunoDTO.getSobrenome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setTelefone(alunoDTO.getTelefone());

        aluno = this.iAlunoRepository.save(aluno);

        LOGGER.info("Aluno atualizado com sucesso.\n");

        return AlunoDTO.of(aluno);
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

            turmaService.deleteAluno(id);

            this.iAlunoRepository.deleteById(id);
        } else {

            throw new IllegalArgumentException(String.format("Aluno de ID [%s] não existe.", id));
        }
    }

    private void validateExistencia(String cpf) {
        if (this.iAlunoRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
    }

    private void validateExistenciaEmail(String email){
        if(iAlunoRepository.existsByEmail(email)){
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
    }

    private Aluno findAluno(Long id) {
        return iAlunoRepository.findById(id).get();
    }
}
