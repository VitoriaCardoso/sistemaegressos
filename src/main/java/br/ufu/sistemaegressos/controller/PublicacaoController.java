package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.PublicacaoDTO;
import br.ufu.sistemaegressos.model.PublicacaoModel;
import br.ufu.sistemaegressos.service.PublicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/publicacoes")
@Tag(name = "Publicações")
public class PublicacaoController {

    private final PublicacaoService publicacaoService;

    @Autowired
    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    @GetMapping("/egresso/{cpf}")
    @Operation(summary = "Buscar publicações por CPF do egresso")
    public List<PublicacaoModel> buscarPublicacoesPorEgresso(@PathVariable String cpf) {
        return publicacaoService.buscarPorEgresso(cpf);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar publicação por ID")
    public ResponseEntity<PublicacaoModel> buscarPorId(@PathVariable UUID id) {
        try {
            PublicacaoModel pub = publicacaoService.buscarPorId(id);
            return ResponseEntity.ok(pub);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar nova publicação")
    public PublicacaoModel criarPublicacao(@RequestBody PublicacaoDTO publicacaoDTO) {
        return publicacaoService.criar(publicacaoDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar publicação")
    public PublicacaoModel atualizarPublicacao(@PathVariable UUID id, @RequestBody PublicacaoDTO publicacaoDTO) {
        return publicacaoService.atualizar(id, publicacaoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir publicação")
    public void excluirPublicacao(@PathVariable UUID id) {
        publicacaoService.excluir(id);
    }
}
