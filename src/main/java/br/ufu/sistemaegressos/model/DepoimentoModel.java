package br.ufu.sistemaegressos.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "depoimento")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepoimentoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String texto_depoimento;

    @Column(nullable = false)
    private LocalDate data_cadastro;

    @Column(nullable = false, length = 7)
    private String privacidade;

    @ManyToOne
    @JoinColumn(name = "id_informacao_academica", referencedColumnName = "id", nullable = false)
    private InformacaoAcademicaModel informacao_academica;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTexto_depoimento() {
        return texto_depoimento;
    }

    public void setTexto_depoimento(String texto_depoimento) {
        this.texto_depoimento = texto_depoimento;
    }

    public LocalDate getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDate data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getPrivacidade() {
        return privacidade;
    }

    public void setPrivacidade(String privacidade) {
        this.privacidade = privacidade;
    }

    public InformacaoAcademicaModel getInformacaoAcademica() {
        return informacao_academica;
    }

    public void setInformacaoAcademica(InformacaoAcademicaModel informacaoAcademica) {
        this.informacao_academica = informacaoAcademica;
    }
}
