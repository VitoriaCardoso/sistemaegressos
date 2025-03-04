package br.ufu.sistemaegressos.dto;

public class PublicacaoDTO {

    private String titulo;
    private String autores;
    private Integer ano_publicacao;
    private String veiculo;
    private String url_publicacao;
    private String matricula_academica;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public Integer getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(Integer ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getUrl_publicacao() {
        return url_publicacao;
    }

    public void setUrl_publicacao(String url_publicacao) {
        this.url_publicacao = url_publicacao;
    }

    public String getMatricula_academica() {
        return matricula_academica;
    }

    public void setMatricula_academica(String matricula_academica) {
        this.matricula_academica = matricula_academica;
    }
}
