package com.hbsis.controle.escolar.turmas;

import com.hbsis.controle.escolar.turnos.Turno;

import javax.persistence.*;

@Entity
@Table(name = "turmas")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", length = 50, unique = true, nullable = false)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "turno_id", referencedColumnName = "id")
    private Turno turno;

    public Turma() {
    }

    public Turma(String codigo, Turno turno) {
        this.codigo = codigo;
        this.turno = turno;
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

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", turno=" + turno +
                '}';
    }
}
