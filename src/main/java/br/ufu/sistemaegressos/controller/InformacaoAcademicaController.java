package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.InformacaoAcademicaDTO;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.service.InformacaoAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{matricula}")
    public InformacaoAcademicaModel atualizarInformacaoAcademica(@PathVariable String matricula, @RequestBody InformacaoAcademicaDTO dto) {
        return informacaoAcademicaService.atualizar(matricula, dto);
    }

    @DeleteMapping("/{matricula}")
    public void excluirInformacaoAcademica(@PathVariable String matricula) {
        informacaoAcademicaService.excluir(matricula);
    }
}
