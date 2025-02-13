CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users (
    id UUID DEFAULT gen_random_uuid() Primary Key,
    nome varchar (100) not null,
    password varchar (100) not null,
    description varchar (200) not null,
    cargo varchar (100) not null,
    email varchar (100) not null,
    date TIMESTAMP not null,
    admin VARCHAR (50) NOT NULL
);