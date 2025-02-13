create table analise_agua(
     analise_id serial primary key not null,
     data_coleta TIMESTAMP not null,
     numero_coleta integer,
     cidade_id integer not null,
     bairro varchar(20) not null,
     logradouro varchar(20),
     cor numeric(5,2) not null,
     turb numeric(5,2) not null,
     ph numeric(5,2) not null,
     cloro numeric(5,2) not null,
     coliformes_totais integer not null,
     coliformes_e_coli integer not null,
     coletor UUID not null,
     periodo varchar (10) not null,
     foreign key (cidade_id) references cidade(cidade_id),
     foreign key (coletor) references users(id)
)