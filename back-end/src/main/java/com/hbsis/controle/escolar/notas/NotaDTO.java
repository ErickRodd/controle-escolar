package com.hbsis.controle.escolar.notas;

import javax.validation.constraints.NotNull;

public class NotaDTO {
    private Long id;

    @NotNull
    private Double valor;

    @NotNull
    private Long alunoId;

    @NotNull
    private Long disciplinaId;

    @NotNull
    private Long bimestreId;

    public NotaDTO() {
    }

    public NotaDTO(Long id, @NotNull Double valor, @NotNull Long alunoId, @NotNull Long disciplinaId, @NotNull Long bimestreId) {
        this.id = id;
        this.valor = valor;
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
        this.bimestreId = bimestreId;
    }

    public static NotaDTO of (Nota nota){
        return new NotaDTO(
                nota.getId(),
                nota.getValor(),
                nota.getAluno().getId(),
                nota.getDisciplina().getId(),
                nota.getBimestre().getId()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Long getBimestreId() {
        return bimestreId;
    }

    public void setBimestreId(Long bimestreId) {
        this.bimestreId = bimestreId;
    }

    @Override
    public String toString() {
        return "NotaDTO{" +
                "id=" + id +
                ", valor=" + valor +
                ", alunoId=" + alunoId +
                ", disciplinaId=" + disciplinaId +
                ", bimestreId=" + bimestreId +
                '}';
    }
}
