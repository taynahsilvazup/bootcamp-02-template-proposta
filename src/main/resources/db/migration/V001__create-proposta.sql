CREATE TABLE proposta (
    id VARCHAR(50) NOT NULL,
    nome VARCHAR(160) NOT NULL,
    email VARCHAR(254) NOT NULL,
    documento VARCHAR(400) NOT NULL,
    endereco VARCHAR(500) NOT NULL,
    salario NUMERIC(8,2) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    PRIMARY KEY (id));