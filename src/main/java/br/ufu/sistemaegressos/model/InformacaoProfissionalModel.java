package br.ufu.sistemaegressos.model;

import br.ufu.sistemaegressos.config.CustomLocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "informacao_profissional")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformacaoProfissionalModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String company_name;

    @Column(nullable = false)
    private String category;

    @Column(length = 45, nullable = false)
    private String job_type;

    @Column(length = 45, nullable = false)
    private String location;

    @Column(length = 45, nullable = false)
    private String job_title;

    @Column(length = 45, nullable = false)
    private String job_level;

    private String function;

    private Double salary;

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @Column(nullable = false)
    private LocalDate start_date;

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate end_date;

    @ManyToOne
    @JoinColumn(name = "informacao_academica", referencedColumnName = "id", nullable = false)
    private InformacaoAcademicaModel informacao_academica;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getJob_level() {
        return job_level;
    }

    public void setJob_level(String job_level) {
        this.job_level = job_level;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
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

    public InformacaoAcademicaModel getInformacao_academica() {
        return informacao_academica;
    }

    public void setInformacao_academica(InformacaoAcademicaModel informacao_academica) {
        this.informacao_academica = informacao_academica;
    }
}
