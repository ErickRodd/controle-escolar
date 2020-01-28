CREATE TABLE turmas(
    id                      BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    codigo                                 VARCHAR(50) UNIQUE NOT NULL,
    turno_id                                           BIGINT NOT NULL,
    CONSTRAINT fk_turno_id FOREIGN KEY (turno_id) REFERENCES turnos(id)
);