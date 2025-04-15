DROP TABLE IF EXISTS publicacao;
DROP TABLE IF EXISTS comunicado_destino;
DROP TABLE IF EXISTS comunicado;
DROP TABLE IF EXISTS informacao_academica_profissional;
DROP TABLE IF EXISTS informacao_profissional;
DROP TABLE IF EXISTS depoimento;
DROP TABLE IF EXISTS informacao_academica;
DROP TABLE IF EXISTS egresso;

CREATE TABLE egresso (
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
    data_atualizacao DATE
);

CREATE TABLE informacao_academica (
    id UUID PRIMARY KEY,
    matricula VARCHAR(45),
    institution_name VARCHAR(255) NOT NULL,
    institution_type VARCHAR(255) NOT NULL,
    course_name VARCHAR(255) NOT NULL,
    course_level VARCHAR(45) NOT NULL,
    campus VARCHAR(45) NOT NULL,
    registration_number VARCHAR(45),
    start_date DATE NOT NULL,
    end_date DATE,
    city VARCHAR(45) NOT NULL,
    state VARCHAR(45) NOT NULL,
    country VARCHAR(45) NOT NULL,
    egresso_cpf VARCHAR(14) NOT NULL,
    ativo BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (egresso_cpf) REFERENCES egresso (cpf)
);

CREATE TABLE depoimento (
    id UUID PRIMARY KEY,
    id_informacao_academica UUID NOT NULL,
    texto_depoimento TEXT NOT NULL,
    data_cadastro DATE NOT NULL,
    privacidade VARCHAR(7) NOT NULL CHECK (privacidade IN ('Público', 'Privado', 'Anônimo')),
    FOREIGN KEY (id_informacao_academica) REFERENCES informacao_academica (id)
);

CREATE TABLE informacao_profissional (
    id UUID PRIMARY KEY,
    empresa VARCHAR(255) NOT NULL,
    categoria VARCHAR(45) NOT NULL,
    tipo VARCHAR(45) NOT NULL,
    localidade VARCHAR(45) NOT NULL,
    cargo VARCHAR(45) NOT NULL,
    nivel_cargo VARCHAR(45) NOT NULL,
    funcao VARCHAR(255),
    media_salarial DECIMAL,
    data_inicio DATE NOT NULL,
    data_fim DATE
);

CREATE TABLE informacao_academica_profissional (
    id_informacao_profissional UUID NOT NULL,
    id_informacao_academica UUID NOT NULL,
    PRIMARY KEY (id_informacao_profissional, id_informacao_academica),
    FOREIGN KEY (id_informacao_profissional) REFERENCES informacao_profissional (id),
    FOREIGN KEY (id_informacao_academica) REFERENCES informacao_academica (id)
);

CREATE TABLE comunicado (
    id UUID PRIMARY KEY,
    titulo VARCHAR(45) NOT NULL,
    texto_comunicado TEXT NOT NULL,
    para_todos BOOLEAN NOT NULL DEFAULT FALSE,
    curso_destino VARCHAR(255),
    nivel_curso_destino VARCHAR(45),
    data_envio DATE NOT NULL DEFAULT NOW()
);

CREATE TABLE comunicado_destino (
    id_comunicado UUID NOT NULL,
    id_informacao_academica UUID NOT NULL,
    PRIMARY KEY (id_comunicado, id_informacao_academica),
    FOREIGN KEY (id_comunicado) REFERENCES comunicado(id) ON DELETE CASCADE,
    FOREIGN KEY (id_informacao_academica) REFERENCES informacao_academica(id) ON DELETE CASCADE
);

CREATE TABLE publicacao (
    id UUID PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autores VARCHAR(255) NOT NULL,
    ano_publicacao INTEGER NOT NULL,
    veiculo VARCHAR(45) NOT NULL,
    url_publicacao VARCHAR(255) NOT NULL,
    id_informacao_academica UUID NOT NULL,
    FOREIGN KEY (id_informacao_academica) REFERENCES informacao_academica (id)
);