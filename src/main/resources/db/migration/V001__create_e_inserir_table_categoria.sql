CREATE TABLE categoria
(
    id serial PRIMARY KEY NOT NULL,
    nome character varying(100) NOT NULL
);

INSERT INTO categoria(nome) VALUES ('Informatica');