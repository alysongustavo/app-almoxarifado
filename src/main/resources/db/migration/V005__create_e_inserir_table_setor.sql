CREATE TABLE setor
(
    id serial PRIMARY KEY NOT NULL,
    nome character varying(100) NOT NULL,
    orgao_id integer NOT NULL,
    responsavel character varying(100) NOT NULL,
    cpf_responsavel character varying(20) NOT NULL UNIQUE,
    senha character varying(255) NOT NULL,
    token character varying(255) NOT NULL UNIQUE,
    pais character varying(35) NOT NULL,
    uf character varying(35) NOT NULL,
    cidade character varying(60) NOT NULL,
    bairro character varying(60) NOT NULL,
    rua character varying(60) NOT NULL,
    numero character varying(10) NOT NULL,    
	telefone character varying(20),
    celular character varying(20) NOT NULL,
    email character varying(100) NOT NULL UNIQUE,
    whatsapp character varying(20),
    FOREIGN KEY (orgao_id) REFERENCES orgao(id)
);

insert into setor (celular,email,telefone,whatsapp,cpf_responsavel,bairro,cidade,numero,pais,rua,uf,nome,responsavel,senha,token,orgao_id) 
values ('(83)98196-8885','nmti@caapora.pb.gov.br','(83)3286-1190','(83)98186-8885','086.703.134-48','CENTRO','CAAPORÃ','S/N','BRASIL','SALOMÃO VELOSO','PARAIBA','NUCLEO MUNICIPAL DE TECNOLOGIA DA INFORMAÇÃO','ALYSON RODRIGO GUSTAVO DA SILVA','ufpb2014','900900900', 1);
insert into setor (celular,email,telefone,whatsapp,cpf_responsavel,bairro,cidade,numero,pais,rua,uf,nome,responsavel,senha,token,orgao_id) 
values ('(83)98196-8885','contato@cookiesoft.com.br','(83)3286-1190','(83)98186-8885','929.300.774-68','CENTRO','CAAPORÃ','148','BRASIL','RUA SEBASTIÃO PEDRO PEREIRA','PARAIBA','GERENCIA DE SISTEMAS','ALYSON RODRIGO GUSTAVO DA SILVA','cookiesoft2017','800800800',2);

