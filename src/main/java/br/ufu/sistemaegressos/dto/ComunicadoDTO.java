package br.ufu.sistemaegressos.dto;

public class ComunicadoDTO {
    private String titulo;
    private String texto_comunicado;
    private Boolean para_todos;
    private String curso_destino;
    private String nivel_curso_destino;

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

    public void setNivel_curso_destino(String nivel_curso_destino) {
        this.nivel_curso_destino = nivel_curso_destino;
    }
}