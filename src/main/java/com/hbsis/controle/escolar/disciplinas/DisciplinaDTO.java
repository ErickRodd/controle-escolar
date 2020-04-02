package com.hbsis.controle.escolar.disciplinas;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class DisciplinaDTO {
    private Long id;

    @NotBlank
    @Length(min = 5, max = 50)
    private String nome;

    public DisciplinaDTO() {
    }

    public DisciplinaDTO(Long id, @NotBlank @Length(min = 5, max = 50) String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static DisciplinaDTO of (Disciplina disciplina){
        return new DisciplinaDTO(
                disciplina.getId(),
                disciplina.getNome()
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

    @Override
    public String toString() {
        return "DisciplinaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
