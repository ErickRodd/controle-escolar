CREATE TABLE disciplinas(
    id      BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    nome                          VARCHAR(50) NOT NULL,
    CONSTRAINT UC_nome UNIQUE (id, nome)
);