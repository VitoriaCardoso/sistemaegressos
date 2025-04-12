package br.ufu.sistemaegressos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "informacao_academica")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformacaoAcademicaModel {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 45)
    private String matricula;                   // Matricula

    @Column(nullable = false)
    private String institution_name;             // Nome Instituição

    @Column(nullable = false)
    private String institution_type;             // Tipo Instituição

    @Column(nullable = false)
    private String course_name;                  // Nome Curso

    @Column(length = 45, nullable = false)
    private String course_level;                 // Titulacao

    @Column(nullable = false)
    private LocalDate start_date;                // Data Ingresso

    private LocalDate end_date;                  // Data Conslusão

    @Column(length = 45, nullable = false)
    private String city;                        // Cidade

    @Column(length = 45, nullable = false)
    private String state;                       // Estado

    @Column(length = 45, nullable = false)
    private String country;                      // Pais

    @Column(nullable = true)
    private String registration_number;           // Código do curso

    @Column(length = 45, nullable = false)
    private String campus;

    @Column(nullable = false)
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "egresso_cpf", referencedColumnName = "cpf", nullable = false)
    private EgressoModel egresso;

    @ManyToMany(mappedBy = "informacao_academica")
    private Set<InformacaoProfissionalModel> informacao_profissional;

    @ManyToMany(mappedBy = "informacao_academica")
    private Set<ComunicadoModel> comunicados;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public String getTipo_instituicao() {
        return institution_type;
    }

    public void setTipo_instituicao(String institution_type) {
        this.institution_type = institution_type;
    }

    public String getNome_curso() {
        return course_name;
    }

    public void setNome_curso(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_level() {
        return course_level;
    }

    public void setCourse_level(String course_level) {
        this.course_level = course_level;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public EgressoModel getEgresso() {
        return egresso;
    }

    public void setEgresso(EgressoModel egresso) {
        this.egresso = egresso;
    }

    public Set<InformacaoProfissionalModel> getInformacao_profissional() {
        return informacao_profissional;
    }

    public void setInformacao_profissional(Set<InformacaoProfissionalModel> informacao_profissional) {
        this.informacao_profissional = informacao_profissional;
    }

    public Set<ComunicadoModel> getComunicados() {
        return comunicados;
    }

    public void setComunicados(Set<ComunicadoModel> comunicados) {
        this.comunicados = comunicados;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}