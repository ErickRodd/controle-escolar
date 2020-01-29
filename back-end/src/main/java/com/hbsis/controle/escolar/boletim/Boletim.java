package com.hbsis.controle.escolar.boletim;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.bimestres.Bimestre;
import com.hbsis.controle.escolar.disciplinas.Disciplina;
import com.hbsis.controle.escolar.notas.Nota;

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

    @OneToMany
    @JoinTable(name = "boletim_notas", joinColumns = @JoinColumn(name = "id_boletim"), inverseJoinColumns = @JoinColumn(name = "id_nota"))
    private List<Nota> notaList;

    public Boletim() {
    }

    public Boletim(Bimestre bimestre, Aluno aluno, List<Disciplina> disciplinaList, List<Nota> notaList) {
        this.bimestre = bimestre;
        this.aluno = aluno;
        this.disciplinaList = disciplinaList;
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

    public List<Nota> getNotaList() {
        return notaList;
    }

    public void setNotaList(List<Nota> notaList) {
        this.notaList = notaList;
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "id=" + id +
                ", bimestre=" + bimestre +
                ", aluno=" + aluno +
                ", disciplinaList=" + disciplinaList +
                ", notas=" + notaList +
                '}';
    }
}
