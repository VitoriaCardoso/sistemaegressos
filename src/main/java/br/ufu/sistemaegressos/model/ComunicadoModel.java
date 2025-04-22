package br.ufu.sistemaegressos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "comunicado")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComunicadoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    private UUID id;

    @Column(length = 45, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String texto_comunicado;

    @Column(nullable = false)
    private Boolean para_todos = false;

    private String curso_destino;

    @Column(length = 45)
    private String nivel_curso_destino;

    @Column(nullable = false)
    private LocalDate data_envio;

    @ManyToMany
    @JoinTable(
            name = "comunicado_destino",
            joinColumns = @JoinColumn(name = "id_comunicado"),
            inverseJoinColumns = @JoinColumn(name = "id_informacao_academica")
    )
    private Set<InformacaoAcademicaModel> informacao_academica = new HashSet<>();

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

    public String getTexto_comunicado() {
        return texto_comunicado;
    }

    public void setTexto_comunicado(String texto_comunicado) {
        this.texto_comunicado = texto_comunicado;
    }

    public Boolean getPara_todos() {
        return para_todos;
    }

    public void setPara_todos(Boolean para_todos) {
        this.para_todos = para_todos;
    }

    public String getCurso_destino() {
        return curso_destino;
    }

    public void setCurso_destino(String curso_destino) {
        this.curso_destino = curso_destino;
    }

    public String getNivel_curso_destino() {
        return nivel_curso_destino;
    }

    public Set<InformacaoAcademicaModel> getInformacao_academica() {
        return informacao_academica;
    }

    public void setInformacao_academica(Set<InformacaoAcademicaModel> informacao_academica) {
        this.informacao_academica = informacao_academica;
    }

    public void setNivel_curso_destino(String nivel_curso_destino) {
        this.nivel_curso_destino = nivel_curso_destino;
    }

    public LocalDate getData_envio() {
        return data_envio;
    }

    public void setData_envio(LocalDate data_envio) {
        this.data_envio = data_envio;
    }
}
