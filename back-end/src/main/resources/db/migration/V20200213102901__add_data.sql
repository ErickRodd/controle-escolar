INSERT INTO alunos (nome, cpf, email, telefone, sobrenome) VALUES (
    'José',
    '17289278038',
    'jose.silva@gmail.com',
    '4791245601',
    'Silva'
);

INSERT INTO turmas (codigo, turno_id) VALUES(
    '1º ano A',
    1
);

INSERT INTO turma_alunos (id_turma, id_aluno) VALUES(
    1,
    1
);

INSERT INTO notas (valor, aluno, disciplina, bimestre) VALUES(
    7.85,
    1,
    1,
    1
);

INSERT INTO notas (valor, aluno, disciplina, bimestre) VALUES(
    8.95,
    1,
    2,
    2
);

INSERT INTO notas (valor, aluno, disciplina, bimestre) VALUES(
    6.5,
    1,
    3,
    3
);

INSERT INTO notas (valor, aluno, disciplina, bimestre) VALUES(
    8,
    1,
    4,
    4
);

INSERT INTO professores (nome, cpf, email, telefone, sobrenome) VALUES (
    'Paulo',
    '09827225081',
    'paulo.silva@gmail.com',
    '47858500231',
    'Silva'
);