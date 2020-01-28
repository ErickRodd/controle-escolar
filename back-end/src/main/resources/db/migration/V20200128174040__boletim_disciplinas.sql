CREATE TABLE boletim_disciplinas(
    id_boletim                                                      BIGINT NOT NULL,
    id_disciplina                                                   BIGINT NOT NULL,
    CONSTRAINT  FK_boletim   FOREIGN KEY  (id_boletim)   REFERENCES    boletins(id),
    CONSTRAINT FK_disciplina FOREIGN KEY (id_disciplina) REFERENCES disciplinas(id)
)