insert into grupo (nome, descricao) values ('ROLE_ADMINISTRADOR','ADMINISTRADOR');
insert into grupo (nome, descricao) values ('ROLE_SOLICITANTE','SOLICITANTE');
insert into grupo (nome, descricao) values ('ROLE_OPERADOR','OPERADOR');

insert into setor (nome,descricao) values ('SECRETARIA DE ADMINISTRAÇÃO','');
insert into setor (nome,descricao) values ('ADMINISTRADOR','');

insert into entidade (celular,email,telefone,whatsapp,cpf_responsavel,bairro,cidade,numero,pais,rua,uf,nome,responsavel,senha,setor_id) 
values ('(83)98196-8885','nmti@caapora.pb.gov.br','(83)3286-1190','(83)98186-8885','086.703.134-48','CENTRO','CAAPORÃ','S/N','BRASIL','SALOMÃO VELOSO','PARAIBA','NUCLEO MUNICIPAL DE TECNOLOGIA DA INFORMAÇÃO','ALYSON RODRIGO GUSTAVO DA SILVA','ufpb2014',1);
insert into entidade (celular,email,telefone,whatsapp,cpf_responsavel,bairro,cidade,numero,pais,rua,uf,nome,responsavel,senha,setor_id) 
values ('(83)98196-8885','contato@cookiesoft.com.br','(83)3286-1190','(83)98186-8885','086.703.134-48','CENTRO','CAAPORÃ','S/N','BRASIL','RUA SEBASTIÃO PEDRO PEREIRA','PARAIBA','GERENCIA DE SISTEMAS','ALYSON RODRIGO GUSTAVO DA SILVA','cookiesoft2017',2);

insert into entidade_grupo (entidade_id,grupo_id) VALUES (1,2);

insert into entidade_grupo (entidade_id,grupo_id) VALUES (2,2);
insert into entidade_grupo (entidade_id,grupo_id) VALUES (2,3);
insert into entidade_grupo (entidade_id,grupo_id) VALUES (2,2);




