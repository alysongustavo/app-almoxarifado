CREATE TABLE notificacao
(
    id serial PRIMARY KEY NOT NULL,
    titulo character varying(100) NOT NULL,
    descricao text NOT NULL,
    status character varying(20) NOT NULL,
    data_cadastro date NOT NULL
);