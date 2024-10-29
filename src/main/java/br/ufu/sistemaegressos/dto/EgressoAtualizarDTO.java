package br.ufu.sistemaegressos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EgressoAtualizarDTO {
    @Size(max = 255)
    private String nomeSocial;
    @Email
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

    public @Size(max = 255) String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(@Size(max = 255) String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public @Email @Size(max = 255) String getEmailSecundario() {
        return emailSecundario;
    }

    public void setEmailSecundario(@Email @Size(max = 255) String emailSecundario) {
        this.emailSecundario = emailSecundario;
    }

    public @Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String telefone) {
        this.telefone = telefone;
    }

    public @Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(@Size(max = 255) @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (xx) xxxxx-xxxx") String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    public @Size(max = 255) Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(@Size(max = 255) Integer idPessoa) {
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

}
