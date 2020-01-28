package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.alunos.AlunoService;
import com.hbsis.controle.escolar.bimestres.Bimestre;
import com.hbsis.controle.escolar.bimestres.BimestreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoletimService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoletimService.class);
    private final IBoletimRepository iBoletimRepository;
    private final BimestreService bimestreService;
    private final AlunoService alunoService;

    public BoletimService(IBoletimRepository iBoletimRepository, BimestreService bimestreService, AlunoService alunoService) {
        this.iBoletimRepository = iBoletimRepository;
        this.bimestreService = bimestreService;
        this.alunoService = alunoService;
    }

    public BoletimDTO save(BoletimDTO boletimDTO) {
        Boletim boletim = new Boletim(
                findBimestre(boletimDTO.getBimestreId()),
                findAluno(boletimDTO.getAlunoId()),
                boletimDTO.getDisciplinaList()
        );

        boletim = this.iBoletimRepository.save(boletim);

        return BoletimDTO.of(boletim);
    }

    public BoletimDTO update(BoletimDTO boletimDTO){
        Optional<Boletim> boletimExistente = this.iBoletimRepository.findById(boletimDTO.getId());

        Boletim boletimNovo = boletimExistente.get();

        boletimNovo.setBimestre(findBimestre(boletimDTO.getBimestreId()));
        boletimNovo.setAluno(findAluno(boletimDTO.getAlunoId()));
        boletimNovo.setDisciplinaList(boletimDTO.getDisciplinaList());

        boletimNovo = this.iBoletimRepository.save(boletimNovo);

        return BoletimDTO.of(boletimNovo);
    }

    public BoletimDTO findById(Long id){
        Optional<Boletim> boletimOptional = this.iBoletimRepository.findById(id);

        if(boletimOptional.isPresent()){
            return BoletimDTO.of(boletimOptional.get());
        }

        throw new IllegalArgumentException("Boletim não encontrado.");
    }

    public void delete(Long id){
        LOGGER.info("Iniciando processo de exclusão de boletim...");

        if(iBoletimRepository.existsById(id)){
            LOGGER.info("Excluindo boletim...");

            this.iBoletimRepository.deleteById(id);
        }else{

            throw new IllegalArgumentException(String.format("Boletim de ID [{}] não encontrado.", id));
        }
    }

    private Bimestre findBimestre(Long id) {
        return this.bimestreService.findByIdOptional(id).get();
    }

    private Aluno findAluno(Long id) {
        return this.alunoService.getOptional(id).get();
    }
}
