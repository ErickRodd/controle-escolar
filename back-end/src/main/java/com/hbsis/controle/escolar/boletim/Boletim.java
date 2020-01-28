package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.bimestres.Bimestre;
import com.hbsis.controle.escolar.disciplinas.Disciplina;

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

    @ManyToMany
    @JoinTable(name = "boletim_disciplinas", joinColumns = @JoinColumn(name = "id_boletim"), inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
    private List<Disciplina> disciplinaList;

    public Boletim() {
    }

    public Boletim(Bimestre bimestre, Aluno aluno, List<Disciplina> disciplinaList) {
        this.bimestre = bimestre;
        this.aluno = aluno;
        this.disciplinaList = disciplinaList;
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

    public List<Disciplina> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(List<Disciplina> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public void setBimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "id=" + id +
                ", bimestre=" + bimestre +
                ", aluno=" + aluno +
                ", disciplinaList=" + disciplinaList +
                '}';
    }
}
