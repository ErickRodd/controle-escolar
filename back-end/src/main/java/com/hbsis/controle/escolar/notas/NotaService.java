package com.hbsis.controle.escolar.notas;

import com.hbsis.controle.escolar.alunos.AlunoService;
import com.hbsis.controle.escolar.bimestres.BimestreService;
import com.hbsis.controle.escolar.disciplinas.DisciplinaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotaService.class);
    private final INotaRepository iNotaRepository;
    private final AlunoService alunoService;
    private final BimestreService bimestreService;
    private final DisciplinaService disciplinaService;

    public NotaService(INotaRepository iNotaRepository, AlunoService alunoService, BimestreService bimestreService, DisciplinaService disciplinaService) {
        this.iNotaRepository = iNotaRepository;
        this.alunoService = alunoService;
        this.bimestreService = bimestreService;
        this.disciplinaService = disciplinaService;
    }

    public NotaDTO save(NotaDTO notaDTO){
        Nota nota = new Nota(
                notaDTO.getValor(),
                alunoService.getOptional(notaDTO.getAlunoId()).get(),
                disciplinaService.findById(notaDTO.getDisciplinaId()),
                bimestreService.findByIdOptional(notaDTO.getBimestreId()).get()
        );

        nota = iNotaRepository.save(nota);

        LOGGER.info("Notas por aluno :" + iNotaRepository.findByAluno_Id(notaDTO.getAlunoId()));

        return NotaDTO.of(nota);
    }

    public NotaDTO update(NotaDTO notaDTO){
        Nota notaExistente = findById(notaDTO.getId());
        notaExistente.setValor(notaDTO.getValor());
        notaExistente.setAluno(alunoService.getOptional(notaDTO.getAlunoId()).get());
        notaExistente.setDisciplina(disciplinaService.findById(notaDTO.getDisciplinaId()));
        notaExistente.setBimestre(bimestreService.findByIdOptional(notaDTO.getBimestreId()).get());

        notaExistente = iNotaRepository.save(notaExistente);

        return NotaDTO.of(notaExistente);

    }

    public void delete(Long id){
        if(iNotaRepository.existsById(id)){
            this.iNotaRepository.deleteById(id);
        }

        throw new IllegalArgumentException("Nota informada não encontrada.");
    }

    public Nota findById(Long id){
        Optional<Nota> notaOptional = iNotaRepository.findById(id);

        if(notaOptional.isPresent()){
            return notaOptional.get();
        }

        throw new IllegalArgumentException(String.format("Nota de ID [%s] não existe.", id));
    }

    public List<Nota> findAll(){
        return iNotaRepository.findAll();
    }

    public List<Nota> findAllById(Long id){
        return iNotaRepository.findByAluno_Id(id);
    }
}
