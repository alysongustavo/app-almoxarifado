CREATE TABLE informativo
(
    id serial PRIMARY KEY NOT NULL,
    titulo character varying(100) NOT NULL,
    descricao text NOT NULL,
    data_cadastro date NOT NULL
);