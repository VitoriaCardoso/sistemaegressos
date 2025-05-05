package br.ufu.sistemaegressos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/oauth")
public class OAuthCallbackController {

    @Value("${oauth.govbr.client-id}")
    private String clientId;

    @Value("${oauth.govbr.client-secret}")
    private String clientSecret;

    @Value("${oauth.govbr.redirect-uri}")
    private String redirectUri;

    @Value("${oauth.govbr.token-uri}")
    private String tokenUri;

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("code", code);
        params.add("redirect_uri", redirectUri);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUri, request, String.class);

        return response;
    }
}
