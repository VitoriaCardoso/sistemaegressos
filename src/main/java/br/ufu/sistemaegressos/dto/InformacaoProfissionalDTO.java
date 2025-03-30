package br.ufu.sistemaegressos.dto;
import java.time.LocalDate;
import java.util.UUID;

public class InformacaoProfissionalDTO {

    private String empresa;
    private String categoria;
    private String tipo;
    private String localidade;
    private String cargo;
    private String nivel_cargo;
    private String funcao;
    private Double media_salarial;
    private LocalDate data_inicio;
    private LocalDate data_fim;
    private UUID id_informacao_academica;

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

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public UUID getId_informacao_academica() {
        return id_informacao_academica;
    }

    public void setId_informacao_academica(UUID id_informacao_academica) {
        this.id_informacao_academica = id_informacao_academica;
    }
}