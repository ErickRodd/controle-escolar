CREATE TABLE alunos(
    id                      BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    nome                                         VARCHAR(100) NOT NULL,
    cpf                                    VARCHAR(11) UNIQUE NOT NULL,
    email                                  VARCHAR(50) UNIQUE NOT NULL,
    telefone                                      VARCHAR(11) NOT NULL,
    turma                                              BIGINT NOT NULL,
    CONSTRAINT FK_aluno_turma FOREIGN KEY (turma) REFERENCES turmas(id)
);