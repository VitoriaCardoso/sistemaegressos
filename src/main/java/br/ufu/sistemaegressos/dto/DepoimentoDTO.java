package br.ufu.sistemaegressos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

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
    private UUID id_informacao_academica; // associa o depoimento com a informação acadêmica

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

    public UUID getId_informacao_academica() {
        return id_informacao_academica;
    }

    public void setId_informacao_academica(UUID id_informacao_academica) {
        this.id_informacao_academica = id_informacao_academica;
    }
}
