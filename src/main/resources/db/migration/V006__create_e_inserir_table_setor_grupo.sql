CREATE TABLE setor_grupo
(
    setor_id integer NOT NULL,
    grupo_id integer NOT NULL,
    FOREIGN KEY (setor_id)
        REFERENCES setor (id),
    FOREIGN KEY (grupo_id)
        REFERENCES grupo (id)
);

insert into setor_grupo (setor_id, grupo_id) VALUES (1,2);

insert into setor_grupo (setor_id, grupo_id) VALUES (2,1);
insert into setor_grupo (setor_id, grupo_id) VALUES (2,2);
insert into setor_grupo (setor_id, grupo_id) VALUES (2,3);