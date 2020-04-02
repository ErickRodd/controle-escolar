CREATE TABLE bimestres(
    id               BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    bimestre                               VARCHAR(11) NOT NULL
);

INSERT INTO bimestres VALUES ('1º Bimestre');
INSERT INTO bimestres VALUES ('2º Bimestre');
INSERT INTO bimestres VALUES ('3º Bimestre');
INSERT INTO bimestres VALUES ('4º Bimestre');