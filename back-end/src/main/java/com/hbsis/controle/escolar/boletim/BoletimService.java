package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.alunos.AlunoService;
import com.hbsis.controle.escolar.bimestres.Bimestre;
import com.hbsis.controle.escolar.bimestres.BimestreService;
import com.hbsis.controle.escolar.notas.NotaService;
import com.hbsis.controle.escolar.turmas.Turma;
import com.hbsis.controle.escolar.turmas.TurmaService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoletimService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BoletimService.class);
    private final IBoletimRepository iBoletimRepository;
    private final BimestreService bimestreService;
    private final AlunoService alunoService;
    private final NotaService notaService;
    private final TurmaService turmaService;

    public BoletimService(IBoletimRepository iBoletimRepository, BimestreService bimestreService, AlunoService alunoService, NotaService notaService, TurmaService turmaService) {
        this.iBoletimRepository = iBoletimRepository;
        this.bimestreService = bimestreService;
        this.alunoService = alunoService;
        this.notaService = notaService;
        this.turmaService = turmaService;
    }

    public BoletimDTO save(BoletimDTO boletimDTO) {
        Boletim boletim = new Boletim(
                findBimestre(boletimDTO.getBimestre()),
                findAluno(boletimDTO.getAlunoId()),
                findTurma(boletimDTO.getTurmaId()),
                notaService.findAllById(boletimDTO.getAlunoId())
        );

        boletim = this.iBoletimRepository.save(boletim);

        return BoletimDTO.of(boletim);
    }

    public BoletimDTO update(BoletimDTO boletimDTO) {
        Optional<Boletim> boletimExistente = this.iBoletimRepository.findById(boletimDTO.getId());

        Boletim boletimNovo = boletimExistente.get();

        boletimNovo.setBimestre(findBimestre(boletimDTO.getBimestre()));
        boletimNovo.setAluno(findAluno(boletimDTO.getAlunoId()));
        boletimNovo.setNotaList(notaService.findAllById(boletimDTO.getAlunoId()));

        boletimNovo = this.iBoletimRepository.save(boletimNovo);

        return BoletimDTO.of(boletimNovo);
    }

    public void exportarJR(Long alunoId) throws FileNotFoundException, JRException {
        if(alunoService.getOptional(alunoId).isPresent()){
            String path = "C:/Users/erick.silva/Desktop/JReports";

            List<Boletim> boletins = iBoletimRepository.findByAluno_Id(alunoId);

            File file = ResourceUtils.getFile("classpath:boletim.jrxml");

            JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletins);

            Map<String, Object> params = new HashMap<>();
            params.put("createdBy", "Erick Rodrigues");

            JasperPrint print = JasperFillManager.fillReport(jr, params, dataSource);

            JasperExportManager.exportReportToPdfFile(print, path + "/boletim.pdf");
        }
        else{
            throw new IllegalArgumentException(String.format("Boletim do aluno de ID [%s] não encontrado.", alunoId));
        }
    }

    public BoletimDTO findById(Long id) {
        Optional<Boletim> boletimOptional = this.iBoletimRepository.findById(id);

        if (boletimOptional.isPresent()) {
            return BoletimDTO.of(boletimOptional.get());
        }

        throw new IllegalArgumentException("Boletim não encontrado.");
    }

    public void delete(Long id) {
        LOGGER.info("Iniciando processo de exclusão de boletim...");

        if (iBoletimRepository.existsById(id)) {
            LOGGER.info("Excluindo boletim...");

            this.iBoletimRepository.deleteById(id);
        } else {

            throw new IllegalArgumentException(String.format("Boletim de ID [%s] não encontrado.", id));
        }
    }

    private Turma findTurma(Long id){
        return this.turmaService.get(id).get();
    }

    private Bimestre findBimestre(Long id) {
        return this.bimestreService.findByIdOptional(id).get();
    }

    private Aluno findAluno(Long id) {
        return this.alunoService.getOptional(id).get();
    }
}
