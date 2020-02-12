package com.hbsis.controle.escolar.turmas;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.alunos.AlunoService;
import com.hbsis.controle.escolar.turnos.Turno;
import com.hbsis.controle.escolar.turnos.TurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurmaService.class);
    private final ITurmaRepository iTurmaRepository;
    private final TurnoService turnoService;

    public TurmaService(ITurmaRepository iTurmaRepository, TurnoService turnoService) {
        this.iTurmaRepository = iTurmaRepository;
        this.turnoService = turnoService;
    }

    public TurmaDTO save(TurmaDTO turmaDTO) {

        Turma turma = new Turma(
                turmaDTO.getCodigo(),
                getTurno(turmaDTO.getTurnoId()),
                turmaDTO.getAlunos()
        );

        turma = this.iTurmaRepository.save(turma);

        return TurmaDTO.of(turma);
    }

    public void deleteAluno(Long id) {

        Optional<Turma> turmaExistente = findByAlunoId(id);

        if (turmaExistente.isPresent()) {
            List<Aluno> alunoList = turmaExistente.get().getAlunoList();

            alunoList.removeIf(al -> al.getId().equals(id));

            Turma turma = turmaExistente.get();

            turma.setAlunoList(alunoList);

            iTurmaRepository.save(turma);
        }
    }

    public TurmaDTO update(TurmaDTO turmaDTO) {
        Optional<Turma> turmaExistente = this.get(turmaDTO.getId());

        Turma turmaNova = turmaExistente.get();

        turmaNova.setCodigo(turmaDTO.getCodigo());
        turmaNova.setTurno(turnoService.getOptional(turmaDTO.getTurnoId()).get());
        turmaNova.setAlunoList(turmaDTO.getAlunos());

        turmaNova = this.iTurmaRepository.save(turmaNova);

        return TurmaDTO.of(turmaNova);
    }

    public TurmaDTO findById(Long id) {
        Optional<Turma> turmaOptional = this.iTurmaRepository.findById(id);

        if (turmaOptional.isPresent()) {
            return TurmaDTO.of(turmaOptional.get());
        }

        throw new IllegalArgumentException("Turma não encontrada.");
    }

    public Optional<Turma> get(Long id) {
        return iTurmaRepository.findById(id);
    }

    public List<Turma> getAll() {
        return this.iTurmaRepository.findAll();
    }

    public void delete(Long id) {
        if (this.iTurmaRepository.existsById(id)) {
            this.iTurmaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(String.format("Turma de ID [%s] não existe.", id));
        }
    }

    public Optional<Turma> findByAlunoId(Long id) {
        return iTurmaRepository.findByAlunoList_Id(id);
    }

    public boolean existsByAlunoId(Long id){
        return iTurmaRepository.existsByAlunoList_Id(id);
    }

    public Turno getTurno(Long id) {
        return this.turnoService.getOptional(id).get();
    }
}
