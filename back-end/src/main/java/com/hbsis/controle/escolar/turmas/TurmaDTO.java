package com.hbsis.controle.escolar.turmas;

import com.hbsis.controle.escolar.alunos.Aluno;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class TurmaDTO {
    private Long id;

    @NotBlank(message = "Código da turma não pode estar vazio.")
    @Length(min = 5, max = 50)
    private String codigo;

    @NotNull(message = "Turno não pode ser nulo.")
    private Long turnoId;

    public TurmaDTO() {
    }

    public TurmaDTO(Long id, String codigo, Long turnoId) {
        this.id = id;
        this.codigo = codigo;
        this.turnoId = turnoId;
    }

    public static TurmaDTO of (Turma turma){
        return new TurmaDTO(
                turma.getId(),
                turma.getCodigo(),
                turma.getTurno().getId()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }

    @Override
    public String toString() {
        return "TurmaDTO{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", turnoId=" + turnoId +
                '}';
    }
}
