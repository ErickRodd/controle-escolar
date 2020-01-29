package com.hbsis.controle.escolar.notas;

import com.hbsis.controle.escolar.alunos.Aluno;

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

    public Nota() {
    }

    public Nota(Double valor, Aluno aluno) {
        this.valor = valor;
        this.aluno = aluno;
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

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", valor=" + valor +
                ", aluno=" + aluno +
                '}';
    }
}
