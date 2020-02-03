ALTER TABLE notas
    ADD disciplina BIGINT NOT NULL;

ALTER TABLE notas
    ADD CONSTRAINT FK_nota_discipina FOREIGN KEY (disciplina) REFERENCES disciplinas(id);

ALTER TABLE notas
    ADD bimestre BIGINT NOT NULL;

ALTER TABLE notas
    ADD CONSTRAINT FK_nota_bimestre FOREIGN KEY (bimestre) REFERENCES bimestres(id);