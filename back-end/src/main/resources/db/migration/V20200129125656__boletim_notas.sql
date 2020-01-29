CREATE TABLE boletim_notas(
    id_boletim                                             BIGINT NOT NULL,
    id_nota                                                BIGINT NOT NULL,
    CONSTRAINT FK_boletim_nota  FOREIGN KEY (id_boletim) REFERENCES boletins(id),
    CONSTRAINT     FK_nota      FOREIGN KEY   (id_nota)  REFERENCES  notas (id)
);