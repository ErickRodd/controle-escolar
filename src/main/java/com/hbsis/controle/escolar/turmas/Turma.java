package com.hbsis.controle.escolar.turmas;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.turnos.Turno;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinTable(name = "turma_alunos", joinColumns = @JoinColumn(name = "id_turma"), inverseJoinColumns = @JoinColumn(name = "id_aluno"))
    private List<Aluno> alunoList;

    public Turma() {
    }

    public Turma(String codigo, Turno turno, List<Aluno> alunoList) {
        this.codigo = codigo;
        this.turno = turno;
        this.alunoList = alunoList;
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

    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", turno=" + turno +
                ", alunoList=" + alunoList +
                '}';
    }
}
