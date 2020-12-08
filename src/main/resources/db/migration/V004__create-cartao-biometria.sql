CREATE TABLE cartao (
    id VARCHAR(50) NOT NULL,
    numero VARCHAR(160) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    PRIMARY KEY (id));

CREATE TABLE biometria (
    id VARCHAR(50) NOT NULL,
    biometria BYTEA NOT NULL,
    cartao_id VARCHAR(50),
    data_cadastro TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cartao_id) REFERENCES cartao(id));