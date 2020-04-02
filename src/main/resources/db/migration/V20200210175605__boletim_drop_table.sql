ALTER TABLE boletins
    DROP CONSTRAINT FK_boletim_bimestre;
    
ALTER TABLE boletins
    DROP CONSTRAINT FK_boletim_aluno;
    
ALTER TABLE boletins
    DROP CONSTRAINT FK_boletim_turma;

ALTER TABLE boletim_notas
    DROP CONSTRAINT FK_boletim_nota;

ALTER TABLE boletim_notas
    DROP CONSTRAINT FK_nota;

DROP TABLE boletim_notas;

DROP TABLE boletins;