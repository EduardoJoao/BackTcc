create table USUARIO(
id bigint auto_increment,
contato varchar(255),
cpfCnpj varchar(255),
email varchar(255),
nome varchar(255),
senha varchar(255),
tpUsuario varchar(255),
idEnd bigint,
dtInclusao timestamp
);

create table ENDERECO(
id bigint auto_increment,
bairro varchar(255),
cep varchar(255),
cidade varchar(255),
endereco varchar(255),
estado varchar(255),
dtInclusao timestamp
);

ALTER TABLE USUARIO
ADD FOREIGN KEY (idEnd)
REFERENCES ENDERECO(id);


INSERT into ENDERECO (bairro, cep, cidade, endereco, estado, dtInclusao ) values ('Imbiribeira', '123', 'Recife', 'Rua. Poeta. Manuel Bandeira, 169', 'PE', CURRENT_TIMESTAMP );
INSERT into USUARIO (contato, cpfCnpj, email, nome, senha, tpUsuario, idEnd, dtInclusao) values ('aaa', '123', 'jell', 'aaa', 'aaa', 'lar', 1, CURRENT_TIMESTAMP);

INSERT into ENDERECO (bairro,cep, cidade, endereco, estado, dtInclusao ) values ('Imbiribeira', '456', 'Recife', 'Av. Mal. Mascarenhas de Morais, 2056', 'PE', CURRENT_TIMESTAMP );
INSERT into USUARIO (contato, cpfCnpj, email, nome, senha, tpUsuario, idEnd, dtInclusao) values ('aaa', '456', 'jellw', 'aaa', 'bbb','lar', 2, CURRENT_TIMESTAMP);

INSERT into ENDERECO (bairro,cep, cidade, endereco, estado, dtInclusao ) values ('Imbiribeira', '789', 'Recife', 'Av. Engenheiro. Alves de Souza, 245', 'PE', CURRENT_TIMESTAMP );
INSERT into USUARIO (contato, cpfCnpj, email, nome, senha, tpUsuario, idEnd, dtInclusao) values ('aaa', '789', 'jellq', 'aaa', 'ccc','ong', 3, CURRENT_TIMESTAMP);

