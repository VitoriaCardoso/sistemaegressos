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
    private String texto_depoimento;
    @NotBlank
    @NotNull
    private LocalDate data_cadastro;
    @NotBlank
    @NotNull
    private String privacidade;

    @NotBlank
    @NotNull
    private String matricula_academica;

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

    public String getPrivacidade() {
        return privacidade;
    }

    public void setPrivacidade(String privacidade) {
        this.privacidade = privacidade;
    }

    public String getMatricula_academica() {
        return matricula_academica;
    }

    public void setMatricula_academica(String matricula_academica) {
        this.matricula_academica = matricula_academica;
    }
}
