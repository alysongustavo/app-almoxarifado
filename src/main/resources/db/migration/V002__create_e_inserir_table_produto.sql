CREATE TABLE produto
(
    id serial PRIMARY KEY NOT NULL,
    nome character varying(100) NOT NULL,
    categoria_id integer NOT NULL,
    descricao character varying(255),
    FOREIGN KEY (categoria_id) REFERENCES categoria (id)
);