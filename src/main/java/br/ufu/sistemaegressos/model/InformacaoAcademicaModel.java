package br.ufu.sistemaegressos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "informacao_academica")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformacaoAcademicaModel {

    @Id
    @GeneratedValue
    private UUID id;

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
    private LocalDate data_ingresso;

    private LocalDate data_conclusao;

    @Column(length = 45, nullable = false)
    private String cidade;

    @Column(length = 45, nullable = false)
    private String estado;

    @Column(length = 45, nullable = false)
    private String pais;

    @Column(nullable = true)
    private String codigo_curso;

    @Column(length = 45, nullable = false)
    private String campus;

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "egresso_cpf", referencedColumnName = "cpf", nullable = false)
    private EgressoModel egresso;

    @ManyToMany(mappedBy = "informacao_academica")
    private Set<InformacaoProfissionalModel> informacao_profissional;

    @ManyToMany(mappedBy = "informacao_academica")
    private Set<ComunicadoModel> comunicados;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDate getData_ingresso() {
        return data_ingresso;
    }

    public void setData_ingresso(LocalDate data_ingresso) {
        this.data_ingresso = data_ingresso;
    }

    public LocalDate getData_conclusao() {
        return data_conclusao;
    }

    public void setData_conclusao(LocalDate data_conclusao) {
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

    public String getCodigo_curso() {
        return codigo_curso;
    }

    public void setCodigo_curso(String codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
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

    public Set<ComunicadoModel> getComunicados() {
        return comunicados;
    }

    public void setComunicados(Set<ComunicadoModel> comunicados) {
        this.comunicados = comunicados;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}