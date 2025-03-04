package br.ufu.sistemaegressos.dto;

import java.sql.Timestamp;

public class InformacaoProfissionalDTO {

    private String empresa;
    private String categoria;
    private String tipo;
    private String localidade;
    private String cargo;
    private String nivel_cargo;
    private String funcao;
    private Double media_salarial;
    private Timestamp data_inicio;
    private Timestamp data_fim;
    private String matricula_academica;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivel_cargo() {
        return nivel_cargo;
    }

    public void setNivel_cargo(String nivel_cargo) {
        this.nivel_cargo = nivel_cargo;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Double getMedia_salarial() {
        return media_salarial;
    }

    public void setMedia_salarial(Double media_salarial) {
        this.media_salarial = media_salarial;
    }

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Timestamp getData_fim() {
        return data_fim;
    }

    public void setData_fim(Timestamp data_fim) {
        this.data_fim = data_fim;
    }

    public String getMatricula_academica() {
        return matricula_academica;
    }

    public void setMatricula_academica(String matriculaAcademica) {
        this.matricula_academica = matriculaAcademica;
    }
}