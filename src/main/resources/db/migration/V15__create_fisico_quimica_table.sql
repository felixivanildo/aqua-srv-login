CREATE TABLE IF NOT EXISTS analise_fisico_quimica
(
    analise_id serial primary key not null,
    data_coleta timestamp without time zone NOT NULL,
    numero_coleta integer,
    cidade_id integer NOT NULL,
    bairro character varying(20) ,
    logradouro character varying(20),
    cor numeric(5,2) NOT NULL,
    turb numeric(5,2) NOT NULL,
    ph numeric(5,2) NOT NULL,
    cloro numeric(5,2) NOT NULL,
    coliformes_totais character varying(10) ,
    coliformes_e_coli character varying(10) ,
    coletor uuid NOT NULL,
    periodo character varying(10),
    observacoes character varying(40),
    analistaid uuid,
    pendente boolean DEFAULT true,
    ic_uso boolean DEFAULT true,
    CONSTRAINT analise_fisico_quimico_cidade_id_fkey FOREIGN KEY (cidade_id)
        REFERENCES public.cidade (cidade_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT analise_fisico_quimico_coletor_fkey FOREIGN KEY (coletor)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_analista_id FOREIGN KEY (analistaid)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)