\connect postgres

CREATE SCHEMA IF NOT EXISTS egressos_ufu;

DROP TABLE IF EXISTS egressos_ufu.egresso;

CREATE TABLE egressos_ufu.egresso (
    cpf VARCHAR(14) PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    nome_social VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    email_secundario VARCHAR(255),
    telefone VARCHAR(15) NOT NULL,
    telefone_secundario VARCHAR(15),
    link_lattes VARCHAR(255),
    link_orcid VARCHAR(255),
    link_linkedin VARCHAR(255),
    data_atualizacao TIMESTAMP
);

DROP TABLE IF EXISTS egressos_ufu.informacao_academica;

CREATE TABLE egressos_ufu.informacao_academica (
    matricula VARCHAR(45) PRIMARY KEY NOT NULL,
    nome_instituicao VARCHAR(255) NOT NULL,
    tipo_instituicao VARCHAR(255) NOT NULL,
    nome_curso VARCHAR(255) NOT NULL,
    titulacao VARCHAR(45) NOT NULL,
    data_ingresso TIMESTAMP NOT NULL,
    data_conclusao TIMESTAMP,
    cidade VARCHAR(45) NOT NULL,
    estado VARCHAR(45) NOT NULL,
    pais VARCHAR(45) NOT NULL,
    egresso_cpf VARCHAR(14) NOT NULL,
    FOREIGN KEY (egresso_cpf) REFERENCES egressos_ufu.egresso (cpf)
);

DROP TABLE IF EXISTS egressos_ufu.depoimento;

CREATE TABLE egressos_ufu.depoimento (
    id UUID PRIMARY KEY,
    matricula_academica VARCHAR(45) NOT NULL,
    texto_depoimento TEXT NOT NULL,
    data_cadastro DATE NOT NULL,
    privacidade VARCHAR(7) NOT NULL CHECK (privacidade IN ('Público', 'Privado', 'Anônimo')),
    FOREIGN KEY (matricula_academica) REFERENCES egressos_ufu.informacao_academica (matricula)
);

DROP TABLE IF EXISTS egressos_ufu.informacao_profissional;

CREATE TABLE egressos_ufu.informacao_profissional (
    id UUID PRIMARY KEY,
    empresa VARCHAR(255) NOT NULL,
    categoria VARCHAR(45) NOT NULL,
    tipo VARCHAR(45) NOT NULL,
    localidade VARCHAR(45) NOT NULL,
    cargo VARCHAR(45) NOT NULL,
    nivel_cargo VARCHAR(45) NOT NULL,
    funcao VARCHAR(255),
    media_salarial DECIMAL,
    data_inicio TIMESTAMP NOT NULL,
    data_fim TIMESTAMP
);

DROP TABLE IF EXISTS egressos_ufu.informacao_academica_profissional;

CREATE TABLE egressos_ufu.informacao_academica_profissional (
    id_informacao_profissional UUID NOT NULL,
    matricula_academica VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_informacao_profissional, matricula_academica),
    FOREIGN KEY (id_informacao_profissional) REFERENCES egressos_ufu.informacao_profissional (id),
    FOREIGN KEY (matricula_academica) REFERENCES egressos_ufu.informacao_academica (matricula)
);

DROP TABLE IF EXISTS egressos_ufu.comunicado;

CREATE TABLE egressos_ufu.comunicado (
    id UUID PRIMARY KEY,
    titulo VARCHAR(45) NOT NULL,
    texto_comunicado TEXT NOT NULL,
    para_todos BOOLEAN NOT NULL DEFAULT FALSE,
    curso_destino VARCHAR(255),
    nivel_curso_destino VARCHAR(45),
    data_envio TIMESTAMP NOT NULL DEFAULT NOW()
);

DROP TABLE IF EXISTS egressos_ufu.comunicado_destino;

CREATE TABLE egressos_ufu.comunicado_destino (
    id_comunicado UUID NOT NULL,
    matricula_academica VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_comunicado, matricula),
    FOREIGN KEY (id_comunicado) REFERENCES egressos_ufu.comunicado(id) ON DELETE CASCADE,
    FOREIGN KEY (matricula_academica) REFERENCES egressos_ufu.informacao_academica(matricula) ON DELETE CASCADE
);

DROP TABLE IF EXISTS egressos_ufu.publicacao;

CREATE TABLE egressos_ufu.publicacao (
    id UUID PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autores VARCHAR(255) NOT NULL,
    ano_publicacao INTEGER NOT NULL,
    veiculo VARCHAR(45) NOT NULL,
    url_publicacao VARCHAR(255) NOT NULL,
    matricula_academica VARCHAR(45) NOT NULL,
    FOREIGN KEY (matricula_academica) REFERENCES egressos_ufu.informacao_academica (matricula)
);