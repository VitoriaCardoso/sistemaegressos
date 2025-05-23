package br.ufu.sistemaegressos.dto;

import java.util.UUID;


public class CursoNameDTO {
    private String label;
    private String value;

    public CursoNameDTO(String  course_name, UUID id) {
        this.label = course_name;
        this.value = id != null ? id.toString() : null;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
