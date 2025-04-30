ALTER TABLE analise_agua
ADD CONSTRAINT fk_analista_id
FOREIGN KEY (analistaid) REFERENCES users(id);
