package com.hbsis.controle.escolar.professores;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ProfessorDTO {
    private Long id;

    @NotBlank
    @Length(min = 2, max = 100)
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
    @Length(min = 10, max = 11)
    private String telefone;

    public ProfessorDTO() {
    }

    public ProfessorDTO(Long id, @NotBlank @Length(min = 2, max = 100) String nome, @NotBlank @Length(min = 2, max = 60) String sobrenome, @CPF @NotBlank @Length(min = 11, max = 11) String cpf, @Email @NotBlank @Length(min = 5, max = 50) String email, @NotBlank @Length(min = 10, max = 11) String telefone) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public static ProfessorDTO of(Professor professor) {
        return new ProfessorDTO(
                professor.getId(),
                professor.getNome(),
                professor.getSobrenome(),
                professor.getCpf(),
                professor.getEmail(),
                professor.getTelefone()
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
        return "ProfessorDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
