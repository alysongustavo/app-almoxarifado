CREATE TABLE orgao
(
    id serial PRIMARY KEY NOT NULL,
    nome character varying(100) NOT NULL,
    descricao character varying(255) NOT NULL
);

INSERT INTO orgao (nome,descricao) VALUES ('SECRETARIA DE ADMINISTRAÇÃO','');
insert into orgao (nome,descricao) VALUES ('ADMINISTRADOR','');