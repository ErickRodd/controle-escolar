package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.disciplinas.Disciplina;

import javax.validation.constraints.NotBlank;
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

    public BoletimDTO() {
    }

    public BoletimDTO(Long id, @NotBlank Long bimestreId, @NotBlank Long alunoId, @NotBlank List<Disciplina> disciplinaList) {
        this.id = id;
        this.bimestreId = bimestreId;
        this.alunoId = alunoId;
        this.disciplinaList = disciplinaList;
    }

    public static BoletimDTO of (Boletim boletim){
        return new BoletimDTO(
                boletim.getId(),
                boletim.getBimestre().getId(),
                boletim.getAluno().getId(),
                boletim.getDisciplinaList()
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

    @Override
    public String toString() {
        return "BoletimDTO{" +
                "id=" + id +
                ", bimestreId=" + bimestreId +
                ", alunoId=" + alunoId +
                ", disciplinaList=" + disciplinaList +
                '}';
    }
}
