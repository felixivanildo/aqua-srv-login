CREATE TABLE cidade (
    cidade_id serial primary key,
    nome_cidade varchar (100) not null,
    unidade_organizacional_id integer,
    ic_uso boolean default true,
    foreign key (unidade_organizacional_id) references unidade_organizacional(unidade_id)
);