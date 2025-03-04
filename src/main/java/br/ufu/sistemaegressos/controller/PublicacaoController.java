package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.PublicacaoDTO;
import br.ufu.sistemaegressos.model.PublicacaoModel;
import br.ufu.sistemaegressos.service.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicacoes")
public class PublicacaoController {

    private final PublicacaoService publicacaoService;

    @Autowired
    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    @GetMapping("/egresso/{cpf}")
    public List<PublicacaoModel> buscarPublicacoesPorEgresso(@PathVariable String cpf) {
        return publicacaoService.buscarPorEgresso(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicacaoModel criarPublicacao(@RequestBody PublicacaoDTO publicacaoDTO) {
        return publicacaoService.criar(publicacaoDTO);
    }

    @PutMapping("/{id}")
    public PublicacaoModel atualizarPublicacao(@PathVariable String id, @RequestBody PublicacaoDTO publicacaoDTO) {
        return publicacaoService.atualizar(id, publicacaoDTO);
    }

    @DeleteMapping("/{id}")
    public void excluirPublicacao(@PathVariable String id) {
        publicacaoService.excluir(id);
    }
}
