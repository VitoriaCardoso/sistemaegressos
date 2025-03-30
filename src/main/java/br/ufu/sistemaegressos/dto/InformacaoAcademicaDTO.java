package br.ufu.sistemaegressos.dto;

import java.time.LocalDate;
import java.util.UUID;

public class InformacaoAcademicaDTO {

    private UUID id;
    private String matricula;
    private String nome_instituicao;
    private String tipo_instituicao;
    private String nome_curso;
    private String titulacao;
    private LocalDate data_ingresso;
    private LocalDate data_conclusao;
    private String cidade;
    private String estado;
    private String pais;
    private String egresso_cpf;
    private String codigo_curso;
    private String campus;
    private Boolean ativo;

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

    public String getEgresso_cpf() {
        return egresso_cpf;
    }

    public void setEgresso_cpf(String egresso_cpf) {
        this.egresso_cpf = egresso_cpf;
    }

    public String  getCodigo_curso() {
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
