CREATE TABLE aviso_viagem (
    id VARCHAR(50) NOT NULL,
    cartao_id VARCHAR(50) NOT NULL,
    destino VARCHAR(200) NOT NULL,
    data_termino DATE NOT NULL,
    cliente_ip VARCHAR(100) NOT NULL,
    user_agent VARCHAR(100) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cartao_id) REFERENCES cartao(id));
