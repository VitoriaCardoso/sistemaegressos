package br.ufu.sistemaegressos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Entity
@Table(name = "egresso", schema = "egressos_ufu")
@Getter
@Setter
public class EgressoModel {


    @Id
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    @Column(name = "nome_social", nullable = false, length = 45)
    private String nomeSocial;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "email_secundario", length = 45)
    private String emailSecundario;

    @Column(name = "telefone", nullable = false, length = 45)
    private String telefone;

    @Column(name = "telefone_secundario", length = 45)
    private String telefoneSecundario;

    @Column(name = "id_pessoa", nullable = false)
    private Integer idPessoa;

    @Column(name = "link_lattes", length = 45)
    private String linkLattes;

    @Column(name = "link_orcid", length = 45)
    private String linkOrcid;

    @Column(name = "link_linkedin", length = 45)
    private String linkLinkedin;

    @Column(name = "data_atualizacao")
    private Timestamp dataAtualizacao;
}

