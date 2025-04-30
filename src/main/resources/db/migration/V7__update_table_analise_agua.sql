ALTER TABLE analise_agua 
ALTER COLUMN coliformes_totais TYPE VARCHAR(10) USING coliformes_totais::VARCHAR,
ALTER COLUMN coliformes_e_coli TYPE VARCHAR(10) USING coliformes_e_coli::VARCHAR;