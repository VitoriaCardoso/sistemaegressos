package br.ufu.sistemaegressos.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class OAuthController {
    @Value("${oauth.govbr.client-id}")
    private String clientId;

    @Value("${oauth.govbr.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.govbr.authorize-uri}")
    private String authorizeUri;

    @GetMapping("/login-govbr")
    public void redirectToGovBr(HttpServletResponse response) throws IOException {
        String state = UUID.randomUUID().toString();

        String url = UriComponentsBuilder.fromHttpUrl(authorizeUri)
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", "openid")
                .queryParam("state", state)
                .build()
                .toUriString();

        response.sendRedirect(url);
    }
}
