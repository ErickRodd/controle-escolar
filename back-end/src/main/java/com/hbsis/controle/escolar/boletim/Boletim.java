package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.bimestres.Bimestre;
import com.hbsis.controle.escolar.notas.Nota;
import com.hbsis.controle.escolar.turmas.Turma;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boletins")
public class Boletim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bimestre", referencedColumnName = "id")
    private Bimestre bimestre;

    @ManyToOne
    @JoinColumn(name = "aluno", referencedColumnName = "id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma", referencedColumnName = "id")
    private Turma turma;

    @OneToMany
    @JoinTable(name = "boletim_notas", joinColumns = @JoinColumn(name = "id_boletim"), inverseJoinColumns = @JoinColumn(name = "id_nota"))
    private List<Nota> notaList;

    public Boletim() {
    }

    public Boletim(Bimestre bimestre, Aluno aluno, Turma turma, List<Nota> notaList) {
        this.bimestre = bimestre;
        this.aluno = aluno;
        this.turma = turma;
        this.notaList = notaList;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public void setBimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
    }

    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "id=" + id +
                ", bimestre=" + bimestre +
                ", aluno=" + aluno +
                ", turma=" + turma +
                ", notaList=" + notaList +
                '}';
    }
}
