package com.hbsis.controle.escolar.bimestres;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class BimestreDTO {
    private Long id;

    @NotBlank
    @Length(min = 11, max = 11)
    private String bimestre;

    public BimestreDTO() {
    }

    public BimestreDTO(Long id, @NotBlank @Length(min = 11, max = 11) String bimestre) {
        this.id = id;
        this.bimestre = bimestre;
    }

    public static BimestreDTO of (Bimestre bimestre){
        return new BimestreDTO(
                bimestre.getId(),
                bimestre.getBimestre()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBimestre() {
        return bimestre;
    }

    public void setBimestre(String bimestre) {
        this.bimestre = bimestre;
    }

    @Override
    public String toString() {
        return "BimestreDTO{" +
                "id=" + id +
                ", bimestre='" + bimestre + '\'' +
                '}';
    }
}
