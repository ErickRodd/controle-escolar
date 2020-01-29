package com.hbsis.controle.escolar.notas;

import com.hbsis.controle.escolar.alunos.AlunoService;
import org.aspectj.weaver.ast.Not;
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

    public NotaService(INotaRepository iNotaRepository, AlunoService alunoService) {
        this.iNotaRepository = iNotaRepository;
        this.alunoService = alunoService;
    }

    public NotaDTO save(NotaDTO notaDTO){
        Nota nota = new Nota(
                notaDTO.getValor(),
                alunoService.getOptional(notaDTO.getAlunoId()).get()
        );

        nota = iNotaRepository.save(nota);

        return NotaDTO.of(nota);
    }

    public NotaDTO update(NotaDTO notaDTO){
        Nota notaExistente = findById(notaDTO.getId());
        notaExistente.setValor(notaDTO.getValor());
        notaExistente.setAluno(alunoService.getOptional(notaDTO.getAlunoId()).get());

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
}
