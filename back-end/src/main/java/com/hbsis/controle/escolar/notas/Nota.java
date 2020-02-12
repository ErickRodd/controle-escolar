package com.hbsis.controle.escolar.notas;

import com.hbsis.controle.escolar.alunos.Aluno;
import com.hbsis.controle.escolar.bimestres.Bimestre;
import com.hbsis.controle.escolar.disciplinas.Disciplina;

import javax.persistence.*;

@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "aluno", referencedColumnName = "id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina", referencedColumnName = "id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "bimestre", referencedColumnName = "id")
    private Bimestre bimestre;

    @Column(name = "descricao", length = 80, nullable = false)
    private String descricao;

    public Nota() {
    }

    public Nota(Double valor, Aluno aluno, Disciplina disciplina, Bimestre bimestre, String descricao) {
        this.valor = valor;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.bimestre = bimestre;
        this.descricao = descricao;
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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public void setBimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", valor=" + valor +
                ", aluno=" + aluno +
                ", disciplina=" + disciplina +
                ", bimestre=" + bimestre +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
