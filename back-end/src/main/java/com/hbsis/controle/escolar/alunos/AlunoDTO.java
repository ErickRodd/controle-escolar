package com.hbsis.controle.escolar.alunos;

import com.hbsis.controle.escolar.turmas.Turma;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class AlunoDTO {
    private Long id;

    @NotBlank
    @Length(min = 5, max = 100)
    private String nome;

    @CPF
    @NotBlank
    @Length(min = 11, max = 11)
    private String cpf;

    @Email
    @NotBlank
    @Length(min = 5, max = 50)
    private String email;

    @NotBlank
    @Length(min = 10, max = 11)
    private String telefone;

    @NotBlank
    private Long boletim;

    @NotBlank
    private List<Turma> turmaList;

    public AlunoDTO() {
    }

    public AlunoDTO(Long id, @NotBlank @Length(min = 5, max = 100) String nome, @CPF @NotBlank @Length(min = 11, max = 11) String cpf, @Email @NotBlank @Length(min = 5, max = 50) String email, @NotBlank @Length(min = 10, max = 11) String telefone, @NotBlank Long boletim, @NotBlank List<Turma> turmaList) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.boletim = boletim;
        this.turmaList = turmaList;
    }

    public static AlunoDTO of(Aluno aluno) {
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getBoletim().getId(),
                aluno.getTurmaList()
        );
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

    public Long getBoletim() {
        return boletim;
    }

    public void setBoletim(Long boletim) {
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
        return "AlunoDTO{" +
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
