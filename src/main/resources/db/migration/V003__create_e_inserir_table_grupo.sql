CREATE TABLE grupo
(
    id serial PRIMARY KEY NOT NULL,
    nome character varying(40) NOT NULL,
    descricao character varying(80) NOT NULL
);

INSERT INTO grupo (nome,descricao) VALUES ('ROLE_ADMINISTRADOR','ADMINISTRADOR');
INSERT INTO grupo (nome,descricao) VALUES ('ROLE_SOLICITANTE','SOLICITANTE');
INSERT INTO grupo (nome,descricao) VALUES ('ROLE_OPERADOR','OPERADOR');