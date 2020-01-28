package com.hbsis.controle.escolar.turnos;

public class TurnoDTO {
    private Long id;
    private String horario;

    public TurnoDTO() {
    }

    public TurnoDTO(Long id, String horario) {
        this.id = id;
        this.horario = horario;
    }

    public static TurnoDTO of (Turno turno){
        return new TurnoDTO(
                turno.getId(),
                turno.getHorario()
        );
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
        return "TurnoDTO{" +
                "id=" + id +
                ", horario='" + horario + '\'' +
                '}';
    }
}
