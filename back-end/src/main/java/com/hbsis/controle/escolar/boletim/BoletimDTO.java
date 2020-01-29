package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.disciplinas.Disciplina;
import com.hbsis.controle.escolar.notas.Nota;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BoletimDTO {
    private Long id;

    @NotNull
    private Long bimestreId;

    @NotNull
    private Long alunoId;

    @NotNull
    private List<Disciplina> disciplinaList;

    @NotNull
    private List<Nota> notaList;

    public BoletimDTO() {
    }

    public BoletimDTO(Long id, @NotNull Long bimestreId, @NotNull Long alunoId, @NotNull List<Disciplina> disciplinaList, @NotNull List<Nota> notaList) {
        this.id = id;
        this.bimestreId = bimestreId;
        this.alunoId = alunoId;
        this.disciplinaList = disciplinaList;
        this.notaList = notaList;
    }

    public static BoletimDTO of (Boletim boletim){
        return new BoletimDTO(
                boletim.getId(),
                boletim.getBimestre().getId(),
                boletim.getAluno().getId(),
                boletim.getDisciplinaList(),
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

    public List<Disciplina> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(List<Disciplina> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }

    public Long getBimestreId() {
        return bimestreId;
    }

    public void setBimestreId(Long bimestreId) {
        this.bimestreId = bimestreId;
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
                ", bimestreId=" + bimestreId +
                ", alunoId=" + alunoId +
                ", disciplinaList=" + disciplinaList +
                ", notaList=" + notaList +
                '}';
    }
}
