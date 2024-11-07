package br.ufu.sistemaegressos.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "depoimento", schema = "egressos_ufu")
public class DepoimentoModel {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "texto_depoimento", nullable = false, length = 100000)
    private String texto_depoimento;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate data_cadastro;

    @Column(name = "privado", nullable = false)
    private Boolean privado;

    @ManyToOne
    @JoinColumn(name = "cpf", referencedColumnName = "cpf", nullable = false)
    private EgressoModelgressoModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Boolean getPrivado() {
        return privado;
    }

    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    public EgressoModel getEgressoModel() {
        return egressoModel;
    }

    public void setEgressoModel(EgressoModel egressoModel) {
        this.egressoModel = egressoModel;
    }
}
