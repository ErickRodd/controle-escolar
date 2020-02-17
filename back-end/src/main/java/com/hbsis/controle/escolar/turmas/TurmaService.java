package com.hbsis.controle.escolar.turmas;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.turnos.Turno;
import com.hbsis.controle.escolar.turnos.TurnoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {
    private final ITurmaRepository iTurmaRepository;
    private final TurnoService turnoService;

    public TurmaService(ITurmaRepository iTurmaRepository, TurnoService turnoService) {
        this.iTurmaRepository = iTurmaRepository;
        this.turnoService = turnoService;
    }

    public TurmaDTO save(TurmaDTO turmaDTO) {

        Turma turma = new Turma(
                turmaDTO.getCodigo(),
                findTurno(turmaDTO.getTurnoId()),
                turmaDTO.getAlunos()
        );

        turma = this.iTurmaRepository.save(turma);

        return TurmaDTO.of(turma);
    }

    public void deleteAluno(Long id) {

        Optional<Turma> turmaExistente = iTurmaRepository.findByAlunoList_Id(id);

        if (turmaExistente.isPresent()) {
            List<Aluno> alunoList = turmaExistente.get().getAlunoList();

            alunoList.removeIf(al -> al.getId().equals(id));

            Turma turma = turmaExistente.get();

            turma.setAlunoList(alunoList);

            iTurmaRepository.save(turma);
        }
    }

    public TurmaDTO update(TurmaDTO turmaDTO) {
        Optional<Turma> turmaExistente = iTurmaRepository.findById(turmaDTO.getId());

        Turma turmaNova = turmaExistente.get();

        turmaNova.setCodigo(turmaDTO.getCodigo());
        turmaNova.setTurno(turnoService.findByIdOptional(turmaDTO.getTurnoId()).get());
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

    public List<Turma> findAll() {
        return this.iTurmaRepository.findAll();
    }

    public void delete(Long id) {
        if (this.iTurmaRepository.existsById(id)) {
            this.iTurmaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(String.format("Turma de ID [%s] não existe.", id));
        }
    }

    public Turma findByAlunoId(Long id) {
        Optional<Turma> turmaOptional = iTurmaRepository.findByAlunoList_Id(id);

        return turmaOptional.get();
    }

    public boolean existsByAlunoId(Long id) {
        return iTurmaRepository.existsByAlunoList_Id(id);
    }

    public Turno findTurno(Long id) {
        return this.turnoService.findByIdOptional(id).get();
    }
}
