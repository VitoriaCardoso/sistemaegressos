package br.ufu.sistemaegressos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EgressoCriarDTO {
    @NotBlank
    @Size(max = 14)
    private String cpf;
    @NotBlank
    @Size(max = 255)
    private String nome;
    @Size(max = 255)
    private String nomeSocial;
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    @Size(max = 255)
    private String emailSecundario;
    @NotBlank
    @Size(max = 255)
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx")
    private String telefone;
    @Size(max = 255)
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx")
    private String telefoneSecundario;
    private Integer idPessoa;
    @Size(max = 255)
    private String linkLattes;
    @Size(max = 255)
    private String linkOrcid;
    @Size(max = 255)
    private String linkLinkedin;
}
