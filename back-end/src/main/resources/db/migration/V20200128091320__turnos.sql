CREATE TABLE turnos(
    id        BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    horario                         VARCHAR(10) NOT NULL,
    CONSTRAINT UC_horario UNIQUE (id, horario)
);

INSERT INTO turnos VALUES ('Matutino');

INSERT INTO turnos VALUES ('Vespertino');

INSERT INTO turnos VALUES ('Noturno');