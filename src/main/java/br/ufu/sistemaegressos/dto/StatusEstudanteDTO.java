package br.ufu.sistemaegressos.dto;

public class StatusEstudanteDTO {
    private String label;
    private Long value;


    public StatusEstudanteDTO() {

    }
    public StatusEstudanteDTO(String status, Long total) {
        this.label = status;
        this.value = total;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
