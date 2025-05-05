package br.ufu.sistemaegressos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "publicacao")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicacaoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autores;

    @Column(nullable = false)
    private Integer ano_publicacao;

    @Column(nullable = false, length = 45)
    private String veiculo;

    @Column(nullable = false, length = 255)
    private String url_publicacao;

    @ManyToOne
    @JoinColumn(name = "id_informacao_academica", referencedColumnName = "id", nullable = false)
    private InformacaoAcademicaModel informacao_academica;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public Integer getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(Integer ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getUrl_publicacao() {
        return url_publicacao;
    }

    public void setUrl_publicacao(String url_publicacao) {
        this.url_publicacao = url_publicacao;
    }

    public InformacaoAcademicaModel getInformacao_academica() {
        return informacao_academica;
    }

    public void setInformacao_academica(InformacaoAcademicaModel informacao_academica) {
        this.informacao_academica = informacao_academica;
    }
}
