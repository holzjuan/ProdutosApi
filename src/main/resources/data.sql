CREATE TABLE produto (
    id varchar(255) not null primary key,
    nome varchar(50) not null,
    descricao varchar(300) null,
    preco numeric(18, 2) not null
);