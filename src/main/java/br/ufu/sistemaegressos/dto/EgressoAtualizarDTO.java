package br.ufu.sistemaegressos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EgressoAtualizarDTO {
    @Size(max = 14)
    private String cpf;
    @Size(max = 255)
    private String nome;
    @Size(max = 255)
    private String nomeSocial;
    @Email
    @Size(max = 255)
    private String email;
    @Size(max = 255)
    private String emailSecundario;
    @Size(max = 255)
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx")
    private String telefone;
    @Size(max = 255)
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx")
    private String telefoneSecundario;
    @Size(max = 255)
    private Integer idPessoa;
    @Size(max = 255)
    private String linkLattes;
    @Size(max = 255)
    private String linkOrcid;
    @Size(max = 255)
    private String linkLinkedin;
}
