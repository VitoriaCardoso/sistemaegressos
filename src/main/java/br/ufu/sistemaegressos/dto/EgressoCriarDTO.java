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
    private String nome_social;
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    @Size(max = 255)
    private String email_secundario;
    @NotBlank
    @Size(max = 255)
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx")
    private String telefone;
    @Size(max = 255)
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx")
    private String telefone_secundario;
    @Size(max = 255)
    private String link_lattes;
    @Size(max = 255)
    private String link_orcid;
    @Size(max = 255)
    private String link_linkedin;

    public @NotBlank @Email @Size(max = 255) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Email @Size(max = 255) String email) {
        this.email = email;
    }

    public @NotBlank @Size(max = 255) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(max = 255) String nome) {
        this.nome = nome;
    }

    public @Size(max = 255) String getNome_social() {
        return nome_social;
    }

    public void setNome_social(@Size(max = 255) String nome_social) {
        this.nome_social = nome_social;
    }

    public @Size(max = 255) String getEmail_secundario() {
        return email_secundario;
    }

    public void setEmail_secundario(@Size(max = 255) String email_secundario) {
        this.email_secundario = email_secundario;
    }

    public @NotBlank @Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank @Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String telefone) {
        this.telefone = telefone;
    }

    public @Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String getTelefone_secundario() {
        return telefone_secundario;
    }

    public void setTelefone_secundario(@Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String telefone_secundario) {
        this.telefone_secundario = telefone_secundario;
    }

    public @Size(max = 255) String getLink_lattes() {
        return link_lattes;
    }

    public void setLink_lattes(@Size(max = 255) String link_lattes) {
        this.link_lattes = link_lattes;
    }

    public @Size(max = 255) String getLink_orcid() {
        return link_orcid;
    }

    public void setLink_orcid(@Size(max = 255) String link_orcid) {
        this.link_orcid = link_orcid;
    }

    public @Size(max = 255) String getLink_linkedin() {
        return link_linkedin;
    }

    public void setLink_linkedin(@Size(max = 255) String link_linkedin) {
        this.link_linkedin = link_linkedin;
    }

    public @NotBlank @Size(max = 14) String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank @Size(max = 14) String cpf) {
        this.cpf = cpf;
    }
}
