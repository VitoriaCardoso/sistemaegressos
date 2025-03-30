package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.InformacaoAcademicaDTO;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.service.InformacaoAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/informacao-academica")
public class InformacaoAcademicaController {

    private final InformacaoAcademicaService informacaoAcademicaService;

    @Autowired
    public InformacaoAcademicaController(InformacaoAcademicaService informacaoAcademicaService) {
        this.informacaoAcademicaService = informacaoAcademicaService;
    }

    @GetMapping("/egresso/{cpf}")
    public List<InformacaoAcademicaModel> buscarPorEgresso(@PathVariable String cpf) {
        return informacaoAcademicaService.buscarPorEgresso(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoAcademicaModel criarInformacaoAcademica(@RequestBody InformacaoAcademicaDTO dto) {
        return informacaoAcademicaService.criar(dto);
    }

    @PutMapping("/{id}")
    public InformacaoAcademicaModel atualizarInformacaoAcademica(@PathVariable UUID id, @RequestBody InformacaoAcademicaDTO dto) {
        return informacaoAcademicaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluirInformacaoAcademica(@PathVariable UUID id) {
        informacaoAcademicaService.excluir(id);
    }
}
