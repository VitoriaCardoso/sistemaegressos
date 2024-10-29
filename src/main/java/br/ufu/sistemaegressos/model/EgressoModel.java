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

    public String getLinkLinkedin() {
        return linkLinkedin;
    }

    public void setLinkLinkedin(String linkLinkedin) {
        this.linkLinkedin = linkLinkedin;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailSecundario() {
        return emailSecundario;
    }

    public void setEmailSecundario(String emailSecundario) {
        this.emailSecundario = emailSecundario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getLinkLattes() {
        return linkLattes;
    }

    public void setLinkLattes(String linkLattes) {
        this.linkLattes = linkLattes;
    }

    public String getLinkOrcid() {
        return linkOrcid;
    }

    public void setLinkOrcid(String linkOrcid) {
        this.linkOrcid = linkOrcid;
    }

    public Timestamp getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Timestamp dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

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

