package com.hbsis.controle.escolar.alunos;

import com.hbsis.controle.escolar.boletim.Boletim;
import com.hbsis.controle.escolar.turmas.Turma;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "telefone", length = 11, nullable = false)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "boletim", referencedColumnName = "id")
    private Boletim boletim;

    @ManyToMany
    @JoinTable(name = "aluno_turma", joinColumns = @JoinColumn(name = "id_aluno"), inverseJoinColumns = @JoinColumn(name = "id_turma"))
    private List<Turma> turmaList;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, String email, String telefone, Boletim boletim, List<Turma> turmaList) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.boletim = boletim;
        this.turmaList = turmaList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boletim getBoletim() {
        return boletim;
    }

    public void setBoletim(Boletim boletim) {
        this.boletim = boletim;
    }

    public List<Turma> getTurmaList() {
        return turmaList;
    }

    public void setTurmaList(List<Turma> turmaList) {
        this.turmaList = turmaList;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", boletim=" + boletim +
                ", turmaList=" + turmaList +
                '}';
    }
}
