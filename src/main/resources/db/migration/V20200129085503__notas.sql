CREATE TABLE notas(
    id                     BIGINT IDENTITY(1, 1) PRIMARY KEY NOT NULL,
    valor                                     DECIMAL(10, 2) NOT NULL,
    aluno                                             BIGINT NOT NULL,
    CONSTRAINT FK_nota_aluno FOREIGN KEY (aluno) REFERENCES alunos(id)
);