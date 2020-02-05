package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.alunos.AlunoService;
import com.hbsis.controle.escolar.bimestres.Bimestre;
import com.hbsis.controle.escolar.bimestres.BimestreService;
import com.hbsis.controle.escolar.notas.Nota;
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
import java.util.*;

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
        if(iBoletimRepository.existsByAluno_IdAndBimestre_Id(boletimDTO.getAlunoId(), boletimDTO.getBimestre())){

            return this.update(boletimDTO);
        }

        Boletim boletim = new Boletim(
                findBimestre(boletimDTO.getBimestre()),
                findAluno(boletimDTO.getAlunoId()),
                findTurma(boletimDTO.getTurmaId()),
                notaService.findAllByAlunoId(boletimDTO.getAlunoId())
        );

        boletim = this.iBoletimRepository.save(boletim);

        return BoletimDTO.of(boletim);
    }

    public BoletimDTO update(BoletimDTO boletimDTO) {
        Optional<Boletim> boletimExistente = this.iBoletimRepository.findById(boletimDTO.getId());

        Boletim boletimNovo = boletimExistente.get();

        boletimNovo.setBimestre(findBimestre(boletimDTO.getBimestre()));
        boletimNovo.setAluno(findAluno(boletimDTO.getAlunoId()));
        boletimNovo.setNotaList(notaService.findAllByAlunoId(boletimDTO.getAlunoId()));

        boletimNovo = this.iBoletimRepository.save(boletimNovo);

        return BoletimDTO.of(boletimNovo);
    }

    public void exportarJR(Long alunoId, Long bimestreId) throws FileNotFoundException, JRException {
        if(iBoletimRepository.existsByAluno_Id(alunoId)){
            List<Boletim> boletim = iBoletimRepository.findByAluno_IdAndBimestre_Id(alunoId, bimestreId);

            List<String> dados = new ArrayList<>();

            dados.add(boletim.get(0).getAluno().getNome());
            dados.add(boletim.get(0).getAluno().getSobrenome());
            dados.add(boletim.get(0).getBimestre().getBimestre());
            dados.add(boletim.get(0).getTurma().getCodigo());
            dados.add(boletim.get(0).getTurma().getTurno().getHorario());

            for(long i = 0; i < 12; i++){
                dados.add(mediaByDisciplina(i));
            }

            File file = ResourceUtils.getFile("classpath:boletim.jrxml");

            JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

            Map<String, Object> params = new HashMap<>();
            params.put("created by", "Erick Rodrigues");

            JasperPrint print = JasperFillManager.fillReport(jr, params, dataSource);

            JasperExportManager.exportReportToPdfFile(print, "C:/Users/erick.silva/Desktop/JReports/boletim.pdf");
        }
        else{
            throw new IllegalArgumentException(String.format("Boletim do aluno de ID [%s] não encontrado.", alunoId));
        }
    }

    private String mediaByDisciplina(Long disciplinaId){
        List<Nota> notasByDisciplina = notaService.findAllByDisciplinaId(disciplinaId);

        if(notasByDisciplina.size() != 0){
            double valorNotas = 0.0;

            for(Nota nota : notasByDisciplina){
                valorNotas += nota.getValor();
            }

            double media = valorNotas/notasByDisciplina.size();

            return Double.toString(media);
        }
        else{
            return " - ";
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
