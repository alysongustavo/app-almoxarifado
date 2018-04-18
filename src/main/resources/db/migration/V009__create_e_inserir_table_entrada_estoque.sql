CREATE TABLE entrada_estoque
(
    id serial PRIMARY KEY NOT NULL,
    estoque_id integer NOT NULL,
    fornecedor_id integer NOT NULL,
    quantidade numeric(10,2) NOT NULL,
    tipo_unidade character varying(30) NOT NULL,
    tipo_embalagem character varying(30) NOT NULL,
    localizacao character varying(255),
    perecivel character varying(5) NOT NULL,
    data_validade date,
    quantidade_minima numeric(10,2) NOT NULL,
    data_recebimento date NOT NULL,
    nota_fiscal character varying(100) NOT NULL,
    valor_notafiscal numeric(10,2) NOT NULL,
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id),
    FOREIGN KEY (estoque_id) REFERENCES estoque (id)
);