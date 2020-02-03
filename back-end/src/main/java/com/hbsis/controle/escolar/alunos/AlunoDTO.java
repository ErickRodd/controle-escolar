package com.hbsis.controle.escolar.alunos;

import com.hbsis.controle.escolar.turmas.Turma;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

public class AlunoDTO {
    private Long id;

    @NotBlank
    @Length(min = 2, max = 20)
    private String nome;

    @NotBlank
    @Length(min = 2, max = 60)
    private String sobrenome;

    @CPF
    @NotBlank
    @Length(min = 11, max = 11)
    private String cpf;

    @Email
    @NotBlank
    @Length(min = 5, max = 50)
    private String email;

    @NotBlank
    @Length(min = 10, max = 11, message = "Telefone deve ter de 10 a 11 caracteres.")
    private String telefone;

    public AlunoDTO() {
    }

    public AlunoDTO(Long id, @NotBlank @Length(min = 2, max = 20) String nome, @NotBlank @Length(min = 2, max = 60) String sobrenome, @CPF @NotBlank @Length(min = 11, max = 11) String cpf, @Email @NotBlank @Length(min = 5, max = 50) String email, @NotBlank @Length(min = 10, max = 11) String telefone) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public static AlunoDTO of(Aluno aluno) {
        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getSobrenome(),
                aluno.getCpf(),
                aluno.getEmail(),
                aluno.getTelefone()
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    @Override
    public String toString() {
        return "AlunoDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
