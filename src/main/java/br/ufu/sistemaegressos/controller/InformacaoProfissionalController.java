package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.InformacaoProfissionalDTO;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.model.InformacaoProfissionalModel;
import br.ufu.sistemaegressos.service.InformacaoProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/informacoes/profissionais")
@CrossOrigin("http://localhost:4200")
public class InformacaoProfissionalController {

    private final InformacaoProfissionalService informacaoProfissionalService;

    @Autowired
    public InformacaoProfissionalController(InformacaoProfissionalService informacaoProfissionalService) {
        this.informacaoProfissionalService = informacaoProfissionalService;
    }

    @GetMapping
    public List<InformacaoProfissionalModel> listarTodos() {
        return informacaoProfissionalService.listarTodos();
    }

    @GetMapping("/editar/{id}")
    public InformacaoProfissionalModel listarPorId(@PathVariable UUID id) {
        return informacaoProfissionalService.listarPeloId(id)
                .orElseThrow(() -> new RuntimeException("Informação profissional não encontrada"));
    }

    @GetMapping("/egresso/{cpf}")
    public List<InformacaoProfissionalModel> buscarPorEgressoCpf(@PathVariable String cpf) {
        return informacaoProfissionalService.buscarPorEgressoCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoProfissionalModel criarInformacaoProfissional(@RequestBody InformacaoProfissionalDTO dto) {
        return informacaoProfissionalService.criarInformacaoProfissional(dto);
    }

    @PutMapping("/{id}")
    public InformacaoProfissionalModel atualizarInformacaoProfissional(@PathVariable UUID id, @RequestBody InformacaoProfissionalDTO dto) {
        return informacaoProfissionalService.atualizarInformacaoProfissional(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirInformacaoProfissional(@PathVariable UUID id) {
        informacaoProfissionalService.excluirInformacaoProfissional(id);
    }
}
