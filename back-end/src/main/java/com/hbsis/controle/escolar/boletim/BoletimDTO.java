package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.notas.Nota;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BoletimDTO {
    private Long id;

    @NotNull
    private Long bimestre;

    @NotNull
    private Long aluno;

    private List<Nota> notaList;

    public BoletimDTO() {
    }

    public BoletimDTO(Long id, @NotNull Long bimestre, @NotNull Long aluno, List<Nota> notaList) {
        this.id = id;
        this.bimestre = bimestre;
        this.aluno = aluno;
        this.notaList = notaList;
    }

    public static BoletimDTO of (Boletim boletim){
        return new BoletimDTO(
                boletim.getId(),
                boletim.getBimestre().getId(),
                boletim.getAluno().getId(),
                boletim.getNotaList()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAluno() {
        return aluno;
    }

    public void setAluno(Long aluno) {
        this.aluno = aluno;
    }

    public Long getBimestre() {
        return bimestre;
    }

    public void setBimestre(Long bimestre) {
        this.bimestre = bimestre;
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
                ", bimestreId=" + bimestre +
                ", alunoId=" + aluno +
                ", notaList=" + notaList +
                '}';
    }
}
