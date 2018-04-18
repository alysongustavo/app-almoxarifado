CREATE TABLE fornecedor
(
    id serial PRIMARY KEY NOT NULL,
    nome_fantasia character varying(100) NOT NULL,
    cnpj character varying(20) NOT NULL,
    responsavel character varying(100) NOT NULL,
    ramo_empresa character varying(60) NOT NULL,
    pais character varying(60) NOT NULL,
    uf character varying(40) NOT NULL,
    cidade character varying(60) NOT NULL,
    bairro character varying(60) NOT NULL,
    rua character varying(100) NOT NULL,
    numero character varying(20) NOT NULL,
    telefone character varying(20) NOT NULL,
    whatsapp character varying(20),
    email character varying(100) NOT NULL,
    site character varying(255)
);