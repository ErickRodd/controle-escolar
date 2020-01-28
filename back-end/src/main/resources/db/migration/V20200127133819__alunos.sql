CREATE TABLE alunos(
    id       BIGINT IDENTITY(1, 1) NOT NULL,
    nome              VARCHAR(100) NOT NULL,
    cpf         VARCHAR(11) UNIQUE NOT NULL,
    email       VARCHAR(50) UNIQUE NOT NULL,
    telefone           VARCHAR(11) NOT NULL
);