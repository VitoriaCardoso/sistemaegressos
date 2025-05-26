package br.ufu.sistemaegressos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "egresso")
@Getter
@Setter
public class EgressoModel {

    @Id
    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    private String nome_social;

    @Column(nullable = false)
    private String email;

    private String email_secundario;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(length = 15)
    private String telefone_secundario;

    private String link_lattes;

    private String link_orcid;

    private String link_linkedin;

    private LocalDate data_atualizacao;

    public String getLink_linkedin() {
        return link_linkedin;
    }

    public void setLink_linkedin(String link_linkedin) {
        this.link_linkedin = link_linkedin;
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

    public String getNome_social() {
        return nome_social;
    }

    public void setNome_social(String nome_social) {
        this.nome_social = nome_social;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_secundario() {
        return email_secundario;
    }

    public void setEmail_secundario(String email_secundario) {
        this.email_secundario = email_secundario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone_secundario() {
        return telefone_secundario;
    }

    public void setTelefone_secundario(String telefone_secundario) {
        this.telefone_secundario = telefone_secundario;
    }

    public String getLink_lattes() {
        return link_lattes;
    }

    public void setLink_lattes(String link_lattes) {
        this.link_lattes = link_lattes;
    }

    public String getLink_orcid() {
        return link_orcid;
    }

    public void setLink_orcid(String link_orcid) {
        this.link_orcid = link_orcid;
    }

    public LocalDate getData_atualizacao() {
        return data_atualizacao;
    }

    public void setData_atualizacao(LocalDate data_atualizacao) {
        this.data_atualizacao = data_atualizacao;
    }
}

