package com.hbsis.controle.escolar.turnos;

import javax.persistence.*;

@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horario", length = 10, unique = true, nullable = false)
    private String horario;

    public Turno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                '}';
    }
}
