CREATE TABLE boletins(
    id                                  BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    bimestre                                                       BIGINT NOT NULL,
    aluno                                                          BIGINT NOT NULL,
    CONSTRAINT FK_boletim_bimestre FOREIGN KEY (bimestre) REFERENCES bimestres(id),
    CONSTRAINT   FK_boletim_aluno  FOREIGN KEY   (aluno)  REFERENCES    alunos(id)
);