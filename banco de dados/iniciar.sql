\connect postgres

CREATE SCHEMA IF NOT EXISTS egressos_ufu;

DROP TABLE IF EXISTS egressos_ufu.egresso;

CREATE TABLE egressos_ufu.egresso (
    cpf VARCHAR(14) PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    nome_social VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    email_secundario VARCHAR(255),
    telefone VARCHAR(45) NOT NULL,
    telefone_secundario VARCHAR(45),
    id_pessoa INT NOT NULL,
    link_lattes VARCHAR(255),
    link_orcid VARCHAR(255),
    link_linkedin VARCHAR(255),
    data_atualizacao TIMESTAMP
);
