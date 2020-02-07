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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
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
        validateBoletim(boletimDTO);

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

        if (iBoletimRepository.findById(boletimDTO.getId()).isPresent()) {
            Boletim boletimNovo = iBoletimRepository.findById(boletimDTO.getId()).get();

            boletimNovo.setBimestre(findBimestre(boletimDTO.getBimestre()));
            boletimNovo.setAluno(findAluno(boletimDTO.getAlunoId()));
            boletimNovo.setNotaList(notaService.findAllByAlunoId(boletimDTO.getAlunoId()));

            boletimNovo = this.iBoletimRepository.save(boletimNovo);

            return BoletimDTO.of(boletimNovo);
        }

        throw new IllegalArgumentException("Boletim não encontrado.");
    }

    public void exportarJR(Long alunoId, Long bimestreId) throws FileNotFoundException, JRException {
        validateExistenciaByAlunoAndBimestre(alunoId, bimestreId);

        List<Boletim> boletim = iBoletimRepository.findByAluno_IdAndBimestre_Id(alunoId, bimestreId);
        List<String> dados = new ArrayList<>();
        List<String> medias = new ArrayList<>();

        dados.add(boletim.get(0).getAluno().getNome());
        dados.add(boletim.get(0).getAluno().getSobrenome());
        dados.add(bimestreId.toString());
        dados.add(boletim.get(0).getTurma().getCodigo());
        dados.add(boletim.get(0).getTurma().getTurno().getHorario());

        if (bimestreId == 1) {
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, bimestreId));
            }
        }

        if (bimestreId == 2) {
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 1));
            }
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 2));
            }
        }

        if (bimestreId == 3) {
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 1));
            }
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 2));
            }
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 3));
            }
        }

        if (bimestreId == 4) {
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 1));
            }
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 2));
            }
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 3));
            }
            for (long i = 1; i < 13; i++) {
                dados.add(mediaByDisciplina(alunoId, i, 4));
            }
            for (int i = 0; i < 12; i++) {
                medias.add(mediaGeral(dados.get(5 + i), dados.get(17 + i), dados.get(29 + i), dados.get(41 + i)));
            }
        }

        File file = ResourceUtils.getFile("classpath:boletim.jrxml");

        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());

        Map<String, Object> params = new HashMap<>();
        params.put("Dados", dados);
        params.put("Medias", medias);

        JasperPrint print = JasperFillManager.fillReport(jr, params, new JREmptyDataSource());

        JasperExportManager.exportReportToPdfFile(print, "C:/Users/erick.silva/Desktop/JReports/boletim.pdf");
    }

    private void validateExistenciaByAlunoAndBimestre(Long alunoId, Long bimestreId) {
        if (!iBoletimRepository.existsByAluno_Id(alunoId)) {
            throw new IllegalArgumentException(String.format("Boletim do aluno de ID [%s] não encontrado.", alunoId));
        }

        if (!iBoletimRepository.existsByAluno_IdAndBimestre_Id(alunoId, bimestreId)) {
            throw new IllegalArgumentException(String.format("Boletim de bimestre %s não encontrado para este aluno.", bimestreId));
        }
    }

    private void validateBoletim(BoletimDTO boletimDTO) {
        if (iBoletimRepository.existsByAluno_IdAndBimestre_Id(boletimDTO.getAlunoId(), boletimDTO.getBimestre())) {
            throw new IllegalArgumentException("Boletim já emitido.");
        }

        if (notaService.findAllByAlunoId(boletimDTO.getAlunoId()).isEmpty()) {
            throw new IllegalArgumentException("Boletim com notas incompletas.");
        }
    }

    private String mediaByDisciplina(Long alunoId, long disciplinaId, long bimestreId) {
        List<Nota> notasByDisciplina = notaService.findByAlunoDisciplinaBimestre(alunoId, disciplinaId, bimestreId);

        if (notasByDisciplina.size() != 0) {
            DecimalFormat df = new DecimalFormat("##.00");

            double valorNotas = 0.0;

            for (Nota nota : notasByDisciplina) {
                valorNotas += nota.getValor();
            }

            Double media = valorNotas / notasByDisciplina.size();

            return df.format(media);
        } else {
            return "-";
        }
    }

    private String mediaGeral(String media1, String media2, String media3, String media4) {
        DecimalFormat df = new DecimalFormat("##.00");

        return df.format((Double.parseDouble(StringUtils.replace(media1, ",", ".")) + Double.parseDouble(StringUtils.replace(media2, ",", ".")) + Double.parseDouble(StringUtils.replace(media3, ",", ".")) + Double.parseDouble(StringUtils.replace(media4, ",", "."))) / 4);
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

    private Turma findTurma(Long id) {
        return this.turmaService.get(id).get();
    }

    private Bimestre findBimestre(Long id) {
        return this.bimestreService.findByIdOptional(id).get();
    }

    private Aluno findAluno(Long id) {
        return this.alunoService.getOptional(id).get();
    }
}
