package com.hbsis.controle.escolar.notas;

import com.hbsis.controle.escolar.alunos.AlunoService;
import com.hbsis.controle.escolar.bimestres.BimestreService;
import com.hbsis.controle.escolar.disciplinas.DisciplinaService;
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
public class NotaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotaService.class);
    private final INotaRepository iNotaRepository;
    private final AlunoService alunoService;
    private final BimestreService bimestreService;
    private final DisciplinaService disciplinaService;
    private final TurmaService turmaService;

    public NotaService(INotaRepository iNotaRepository, AlunoService alunoService, BimestreService bimestreService, DisciplinaService disciplinaService, TurmaService turmaService) {
        this.iNotaRepository = iNotaRepository;
        this.alunoService = alunoService;
        this.bimestreService = bimestreService;
        this.disciplinaService = disciplinaService;
        this.turmaService = turmaService;
    }

    public NotaDTO save(NotaDTO notaDTO) {
        Nota nota = new Nota(
                notaDTO.getValor(),
                alunoService.getOptional(notaDTO.getAlunoId()).get(),
                disciplinaService.findById(notaDTO.getDisciplinaId()),
                bimestreService.findByIdOptional(notaDTO.getBimestreId()).get()
        );

        nota = iNotaRepository.save(nota);

        return NotaDTO.of(nota);
    }

    public NotaDTO update(NotaDTO notaDTO) {
        Nota notaExistente = findById(notaDTO.getId());
        notaExistente.setValor(notaDTO.getValor());
        notaExistente.setAluno(alunoService.getOptional(notaDTO.getAlunoId()).get());
        notaExistente.setDisciplina(disciplinaService.findById(notaDTO.getDisciplinaId()));
        notaExistente.setBimestre(bimestreService.findByIdOptional(notaDTO.getBimestreId()).get());

        notaExistente = iNotaRepository.save(notaExistente);

        return NotaDTO.of(notaExistente);
    }

    public void delete(Long id) {
        if (iNotaRepository.existsById(id)) {
            this.iNotaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Nota informada não encontrada.");
        }
    }

    public Nota findById(Long id) {
        Optional<Nota> notaOptional = iNotaRepository.findById(id);

        if (notaOptional.isPresent()) {
            return notaOptional.get();
        }

        throw new IllegalArgumentException(String.format("Nota de ID [%s] não existe.", id));
    }

    public List<Nota> findAll() {
        return iNotaRepository.findAll();
    }

    public List<Nota> findAllByAlunoId(Long alunoId) {
        return iNotaRepository.findByAluno_Id(alunoId);
    }

    public List<Nota> findByAlunoDisciplinaBimestre(Long alunoId, Long disciplinaId, Long bimestreId) {
        return iNotaRepository.findByAluno_IdAndDisciplina_IdAndBimestre_Id(alunoId, disciplinaId, bimestreId);
    }


    public void exportarJR(Long alunoId, Long bimestreId) throws FileNotFoundException, JRException {
        validateExistenciaByAlunoAndBimestre(alunoId, bimestreId);

        List<Nota> notas = iNotaRepository.findByAluno_IdAndBimestre_Id(alunoId, bimestreId);
        List<String> dados = new ArrayList<>();
        List<String> medias = new ArrayList<>();

        dados.add(notas.get(0).getAluno().getNome());
        dados.add(notas.get(0).getAluno().getSobrenome());
        dados.add(bimestreId.toString());
        dados.add(turmaService.findByAlunoId(notas.get(0).getAluno().getId()).get().getCodigo());
        dados.add(turmaService.findByAlunoId(notas.get(0).getAluno().getId()).get().getTurno().getHorario());

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
                LOGGER.info("Média " + (i + 1));
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
        if (!iNotaRepository.existsByAluno_Id(alunoId)) {
            throw new IllegalArgumentException(String.format("Boletim do aluno de ID [%s] não encontrado.", alunoId));
        }

        if (!iNotaRepository.existsByAluno_IdAndBimestre_Id(alunoId, bimestreId)) {
            throw new IllegalArgumentException(String.format("Boletim de bimestre %s não encontrado para este aluno.", bimestreId));
        }
    }

    private String mediaByDisciplina(Long alunoId, long disciplinaId, long bimestreId) {
        List<Nota> notasByDisciplina = findByAlunoDisciplinaBimestre(alunoId, disciplinaId, bimestreId);

        if (!notasByDisciplina.isEmpty()) {
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

        String[] medias = {media1, media2, media3, media4};
        Double[] mediasDouble = new Double[4];

        for (int i = 0; i < 4; i++) {
            if (StringUtils.containsAny(medias[i], "-")) {
                medias[i] = StringUtils.replaceChars(medias[i], "-", "0");
            }

            LOGGER.info("n" + (i + 1) + " = " + medias[i]);

            mediasDouble[i] = Double.parseDouble(medias[i].replace(",", "."));
        }

        LOGGER.info("media geral = " + (mediasDouble[0] + mediasDouble[1] + mediasDouble[2] + mediasDouble[3]) / 4 + "\n");

        return df.format((mediasDouble[0] + mediasDouble[1] + mediasDouble[2] + mediasDouble[3]) / 4);
    }
}
