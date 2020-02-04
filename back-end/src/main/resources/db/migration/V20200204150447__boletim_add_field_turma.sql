ALTER TABLE boletins
    ADD turma BIGINT NOT NULL;

ALTER TABLE boletins
    ADD CONSTRAINT FK_boletim_turma FOREIGN KEY (turma) REFERENCES turmas(id);