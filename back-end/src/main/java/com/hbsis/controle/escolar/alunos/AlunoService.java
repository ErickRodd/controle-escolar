package com.hbsis.controle.escolar.alunos;

import com.hbsis.controle.escolar.turmas.Turma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoService.class);
    private IAlunoRepository iAlunoRepository;

    public AlunoService(IAlunoRepository iAlunoRepository) {
        this.iAlunoRepository = iAlunoRepository;
    }

    /*public AlunoDTO save(AlunoDTO alunoDTO) {
        this.validate(alunoDTO);
        this.validateExistencia(alunoDTO.getCpf());

        LOGGER.info("Cadastrando novo aluno...");

        Aluno aluno = new Aluno(
                alunoDTO.getNome(),
                alunoDTO.getCpf(),
                alunoDTO.getEmail(),
                alunoDTO.getTelefone(),
                alunoDTO.getBoletim(),
                alunoDTO.getTurmaList()
        );

        aluno = this.iAlunoRepository.save(aluno);

        return AlunoDTO.of(aluno);
    }*/

    public AlunoDTO update(AlunoDTO alunoDTO) {
        this.validate(alunoDTO);

        LOGGER.info("Atualizando aluno de ID [{}]", alunoDTO.getId());

        Optional<Aluno> alunoExistente = this.iAlunoRepository.findById(alunoDTO.getId());

        Aluno alunoNovo = alunoExistente.get();
        alunoNovo.setNome(alunoDTO.getNome());
        alunoNovo.setCpf(alunoDTO.getCpf());
        alunoNovo.setEmail(alunoDTO.getEmail());
        alunoNovo.setTelefone(alunoDTO.getTelefone());

        alunoNovo = this.iAlunoRepository.save(alunoNovo);

        return AlunoDTO.of(alunoNovo);
    }

    public AlunoDTO get(Long id) {
        LOGGER.info("Procurand por aluno de ID [{}]", id);

        Optional<Aluno> alunoOptional = this.iAlunoRepository.findById(id);

        if (alunoOptional.isPresent()) {
            return AlunoDTO.of(alunoOptional.get());
        }

        throw new IllegalArgumentException(String.format("Aluno de ID [{}] não existe.", id));
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

            throw new IllegalArgumentException(String.format("Aluno de ID {} não existe.", id));
        }
    }

    private void validate(AlunoDTO alunoDTO) {
        LOGGER.info("Validando novo aluno...");

        if (alunoDTO == null) {
            throw new IllegalArgumentException("Objeto está nulo.");
        }
    }

    private void validateExistencia(String cpf) {
        if (this.iAlunoRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
    }

/*    private Turma findTurma(Long id){
        Optional<Turma> turmaOptional;
    }*/
}
