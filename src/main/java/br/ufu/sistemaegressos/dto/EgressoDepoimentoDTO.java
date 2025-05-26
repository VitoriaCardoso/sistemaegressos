package br.ufu.sistemaegressos.dto;

public class EgressoDepoimentoDTO {
    private String nome;
    private String courseName;
    private String courseLevel;
    private String campus;
    private String textoDepoimento;

    public EgressoDepoimentoDTO() {}

    public EgressoDepoimentoDTO(String nome, String courseName, String courseLevel, String campus, String textoDepoimento) {
        this.nome = nome;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.campus = campus;
        this.textoDepoimento = textoDepoimento;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLevel() {
        return courseLevel;
    }
    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getCampus() {
        return campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getTextoDepoimento() {
        return textoDepoimento;
    }
    public void setTextoDepoimento(String textoDepoimento) {
        this.textoDepoimento = textoDepoimento;
    }
}
