package br.ufu.sistemaegressos.dto;

public class CursoCampusTitulacaoDTO {
    private String curso;
    private String campus;
    private String titulacao;
    private String semestre;
    private Long total;

    public CursoCampusTitulacaoDTO() {}

    public CursoCampusTitulacaoDTO(String curso, String campus, String titulacao, String semestre, Long total) {
        this.curso = curso;
        this.campus = campus;
        this.titulacao = titulacao;
        this.semestre = semestre;
        this.total = total;
    }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }

    public String getTitulacao() { return titulacao; }
    public void setTitulacao(String titulacao) { this.titulacao = titulacao; }

    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
