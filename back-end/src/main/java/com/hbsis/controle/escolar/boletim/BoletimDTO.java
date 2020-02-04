package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.notas.Nota;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BoletimDTO {
    private Long id;

    @NotNull
    private Long bimestre;

    @NotNull
    private Long alunoId;

    @NotNull
    private Long turmaId;

    private List<Nota> notaList;

    public BoletimDTO() {
    }

    public BoletimDTO(Long id, @NotNull Long bimestre, @NotNull Long alunoId, @NotNull Long turmaId, List<Nota> notaList) {
        this.id = id;
        this.bimestre = bimestre;
        this.alunoId = alunoId;
        this.notaList = notaList;
        this.turmaId = turmaId;
    }

    public static BoletimDTO of (Boletim boletim){
        return new BoletimDTO(
                boletim.getId(),
                boletim.getBimestre().getId(),
                boletim.getAluno().getId(),
                boletim.getTurma().getId(),
                boletim.getNotaList()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getBimestre() {
        return bimestre;
    }

    public void setBimestre(Long bimestre) {
        this.bimestre = bimestre;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public String toString() {
        return "BoletimDTO{" +
                "id=" + id +
                ", bimestre=" + bimestre +
                ", alunoId=" + alunoId +
                ", turmaId=" + turmaId +
                ", notaList=" + notaList +
                '}';
    }
}
