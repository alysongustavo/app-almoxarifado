CREATE TABLE solicitacao
(
    id serial PRIMARY KEY NOT NULL,
    setor_id integer NOT NULL,
    numero character varying(255) NOT NULL,
    data_solicitacao date NOT NULL,
    status_solicitacao character varying(20) NOT NULL,
    quant_item_solicitado numeric(10,2) NOT NULL,
    quant_item_atendido numeric(10,2) NOT NULL,
    observacao text,
    FOREIGN KEY (setor_id) REFERENCES setor (id)
);