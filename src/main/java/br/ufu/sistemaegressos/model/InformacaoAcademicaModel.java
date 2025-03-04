package br.ufu.sistemaegressos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "informacao_academica", schema = "egressos_ufu")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformacaoAcademicaModel {

    @Id
    @Column(length = 45)
    private String matricula;

    @Column(nullable = false)
    private String nome_instituicao;

    @Column(nullable = false)
    private String tipo_instituicao;

    @Column(nullable = false)
    private String nome_curso;

    @Column(length = 45, nullable = false)
    private String titulacao;

    @Column(nullable = false)
    private Timestamp data_ingresso;

    private Timestamp data_conclusao;

    @Column(length = 45, nullable = false)
    private String cidade;

    @Column(length = 45, nullable = false)
    private String estado;

    @Column(length = 45, nullable = false)
    private String pais;

    @ManyToOne
    @JoinColumn(name = "egresso_cpf", referencedColumnName = "cpf", nullable = false)
    private EgressoModel egresso;

    @ManyToMany(mappedBy = "informacao_academica")
    private Set<InformacaoProfissionalModel> informacao_profissional;

    @ManyToMany(mappedBy = "informacao_academica")
    private Set<ComunicadoModel> comunicados;

    public Set<ComunicadoModel> getComunicados() {
        return comunicados;
    }

    public void setComunicados(Set<ComunicadoModel> comunicados) {
        this.comunicados = comunicados;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome_instituicao() {
        return nome_instituicao;
    }

    public void setNome_instituicao(String nome_instituicao) {
        this.nome_instituicao = nome_instituicao;
    }

    public String getTipo_instituicao() {
        return tipo_instituicao;
    }

    public void setTipo_instituicao(String tipo_instituicao) {
        this.tipo_instituicao = tipo_instituicao;
    }

    public String getNome_curso() {
        return nome_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public Timestamp getData_ingresso() {
        return data_ingresso;
    }

    public void setData_ingresso(Timestamp data_ingresso) {
        this.data_ingresso = data_ingresso;
    }

    public Timestamp getData_conclusao() {
        return data_conclusao;
    }

    public void setData_conclusao(Timestamp data_conclusao) {
        this.data_conclusao = data_conclusao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public EgressoModel getEgresso() {
        return egresso;
    }

    public void setEgresso(EgressoModel egresso) {
        this.egresso = egresso;
    }

    public Set<InformacaoProfissionalModel> getInformacao_profissional() {
        return informacao_profissional;
    }

    public void setInformacao_profissional(Set<InformacaoProfissionalModel> informacao_profissional) {
        this.informacao_profissional = informacao_profissional;
    }
}