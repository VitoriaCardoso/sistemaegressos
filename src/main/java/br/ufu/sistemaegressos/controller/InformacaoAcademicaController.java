package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.InformacaoAcademicaDTO;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.service.InformacaoAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/informacoes/academicas")
@CrossOrigin("http://localhost:4200")
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

    @GetMapping("/editar/{id}")
    public List<InformacaoAcademicaModel> buscarPorInformacaoAcademica(@PathVariable UUID id) {
        System.out.println("Dados enviados para o frontend: " + informacaoAcademicaService.buscarPorInformacaoAcademica(id));
        return informacaoAcademicaService.buscarPorInformacaoAcademica(id);
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirInformacaoAcademica(@PathVariable UUID id) {
        informacaoAcademicaService.excluir(id);
    }

    @GetMapping("/sem-depoimento")
    public List<InformacaoAcademicaModel> buscarInformacoesAcademicasSemDepoimento(@RequestParam String cpf) {
        return informacaoAcademicaService.buscarInformacoesAcademicasSemDepoimento(cpf);
    }
}
