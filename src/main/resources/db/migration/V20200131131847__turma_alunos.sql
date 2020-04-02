CREATE TABLE turma_alunos(
    id_turma    BIGINT NOT NULL,
    id_aluno        BIGINT NULL,
    CONSTRAINT FK_id_turma FOREIGN KEY (id_turma) REFERENCES turmas(id),
    CONSTRAINT FK_id_aluno FOREIGN KEY (id_aluno) REFERENCES alunos(id)
);