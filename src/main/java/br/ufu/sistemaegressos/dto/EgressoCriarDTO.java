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

    public @Size(max = 255) String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(@Size(max = 255) String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public @Size(max = 255) String getEmailSecundario() {
        return emailSecundario;
    }

    public void setEmailSecundario(@Size(max = 255) String emailSecundario) {
        this.emailSecundario = emailSecundario;
    }

    public @NotBlank @Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank @Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String telefone) {
        this.telefone = telefone;
    }

    public @Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(@Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public @Size(max = 255) String getLinkLattes() {
        return linkLattes;
    }

    public void setLinkLattes(@Size(max = 255) String linkLattes) {
        this.linkLattes = linkLattes;
    }

    public @Size(max = 255) String getLinkOrcid() {
        return linkOrcid;
    }

    public void setLinkOrcid(@Size(max = 255) String linkOrcid) {
        this.linkOrcid = linkOrcid;
    }

    public @Size(max = 255) String getLinkLinkedin() {
        return linkLinkedin;
    }

    public void setLinkLinkedin(@Size(max = 255) String linkLinkedin) {
        this.linkLinkedin = linkLinkedin;
    }

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

    public @NotBlank @Size(max = 14) String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank @Size(max = 14) String cpf) {
        this.cpf = cpf;
    }
}
