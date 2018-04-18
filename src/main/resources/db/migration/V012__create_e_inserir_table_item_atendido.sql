CREATE TABLE item_atendido
(
    id serial PRIMARY KEY NOT NULL,
    solicitacao_id integer NOT NULL,
    estoque_id integer NOT NULL,
    quant_atendida numeric(19,2) NOT NULL,
    FOREIGN KEY (estoque_id)
        REFERENCES estoque (id),
    FOREIGN KEY (solicitacao_id)
        REFERENCES solicitacao (id)
);