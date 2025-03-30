package br.ufu.sistemaegressos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "informacao_profissional")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformacaoProfissionalModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String empresa;

    @Column(nullable = false)
    private String categoria;

    @Column(length = 45, nullable = false)
    private String tipo;

    @Column(length = 45, nullable = false)
    private String localidade;

    @Column(length = 45, nullable = false)
    private String cargo;

    @Column(length = 45, nullable = false)
    private String nivel_cargo;

    private String funcao;

    private Double media_salarial;

    @Column(nullable = false)
    private LocalDate data_inicio;

    private LocalDate data_fim;

    @ManyToMany
    @JoinTable(
            name = "informacao_academica_profissional",
            joinColumns = @JoinColumn(name = "id_informacao_profissional"),
            inverseJoinColumns = @JoinColumn(name = "id_informacao_academica")
    )
    private Set<InformacaoAcademicaModel> informacao_academica = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Set<InformacaoAcademicaModel> getInformacao_academica() {
        return informacao_academica;
    }

    public void setInformacao_academica(Set<InformacaoAcademicaModel> informacao_academica) {
        this.informacao_academica = informacao_academica;
    }
}
