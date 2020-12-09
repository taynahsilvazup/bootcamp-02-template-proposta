CREATE TABLE bloqueio (
    id VARCHAR(50) NOT NULL,
    cartao_id VARCHAR(50) NOT NULL,
    cliente_ip VARCHAR(100) NOT NULL,
    user_agent VARCHAR(100) NOT NULL,
    data_bloqueio TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cartao_id) REFERENCES cartao(id));
