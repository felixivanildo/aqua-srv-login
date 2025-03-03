create table unidade_organizacional (
    unidade_id serial primary key,
    nome_unidade varchar (100) not null,
    ic_uso boolean default true,
    created_at TIMESTAMP DEFAULT  CURRENT_TIMESTAMP
)