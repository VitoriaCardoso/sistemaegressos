package br.ufu.sistemaegressos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class DepoimentoDTO {
    @NotBlank
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    private String texto_depoimento;
    @NotBlank
    @NotNull
    private LocalDate data_cadastro;
    @NotBlank
    @NotNull
    private Boolean privado;

    public @NotBlank @NotNull String getId() {
        return id;
    }

    public void setId(@NotBlank @NotNull String id) {
        this.id = id;
    }

    public @NotBlank @NotNull String getTexto_depoimento() {
        return texto_depoimento;
    }

    public void setTexto_depoimento(@NotBlank @NotNull String texto_depoimento) {
        this.texto_depoimento = texto_depoimento;
    }

    public @NotBlank @NotNull LocalDate getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(@NotBlank @NotNull LocalDate data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public @NotBlank @NotNull Boolean getPrivado() {
        return privado;
    }

    public void setPrivado(@NotBlank @NotNull Boolean privado) {
        this.privado = privado;
    }
}
