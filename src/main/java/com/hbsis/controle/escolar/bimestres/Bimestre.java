package com.hbsis.controle.escolar.bimestres;

import javax.persistence.*;

@Entity
@Table(name = "bimestres")
public class Bimestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bimestre", length = 11, unique = true, nullable = false)
    private String bimestre;

    public Bimestre() {
    }

    public Bimestre(String bimestre) {
        this.bimestre = bimestre;
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
        return "Bimestre{" +
                "id=" + id +
                ", bimestre='" + bimestre + '\'' +
                '}';
    }
}
