package com.hbsis.controle.escolar.notas;

import javax.validation.constraints.NotNull;

public class NotaDTO {
    private Long id;

    @NotNull
    private Double valor;

    @NotNull
    private Long alunoId;

    public NotaDTO() {
    }

    public NotaDTO(Long id, @NotNull Double valor, @NotNull Long alunoId) {
        this.id = id;
        this.valor = valor;
        this.alunoId = alunoId;
    }

    public static NotaDTO of (Nota nota){
        return new NotaDTO(
                nota.getId(),
                nota.getValor(),
                nota.getAluno().getId()
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

    @Override
    public String toString() {
        return "NotaDTO{" +
                "id=" + id +
                ", valor=" + valor +
                ", alunoId=" + alunoId +
                '}';
    }
}
