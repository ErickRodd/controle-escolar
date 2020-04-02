package com.hbsis.controle.escolar.notas;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Length(min = 5, max = 80)
    private String descricao;

    public NotaDTO() {
    }

    public NotaDTO(Long id, @NotNull Double valor, @NotNull Long alunoId, @NotNull Long disciplinaId, @NotNull Long bimestreId, @NotBlank @Length(min = 5, max = 80) String descricao) {
        this.id = id;
        this.valor = valor;
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
        this.bimestreId = bimestreId;
        this.descricao = descricao;
    }

    public static NotaDTO of(Nota nota) {
        return new NotaDTO(
                nota.getId(),
                nota.getValor(),
                nota.getAluno().getId(),
                nota.getDisciplina().getId(),
                nota.getBimestre().getId(),
                nota.getDescricao()
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "NotaDTO{" +
                "id=" + id +
                ", valor=" + valor +
                ", alunoId=" + alunoId +
                ", disciplinaId=" + disciplinaId +
                ", bimestreId=" + bimestreId +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
