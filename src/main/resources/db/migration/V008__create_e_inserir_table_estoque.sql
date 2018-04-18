CREATE TABLE estoque
(
    id serial PRIMARY KEY NOT NULL,
    produto_id integer,
    quant_total numeric(10,2) NOT NULL,
    data_atualizacao date NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto (id)
);