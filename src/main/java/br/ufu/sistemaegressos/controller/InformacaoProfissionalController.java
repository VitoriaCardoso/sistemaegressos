package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.InformacaoProfissionalDTO;
import br.ufu.sistemaegressos.model.InformacaoProfissionalModel;
import br.ufu.sistemaegressos.service.InformacaoProfissionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/informacoes/profissionais")
@Tag(name = "Informações Profissionais")
@CrossOrigin("http://localhost:4200")
public class InformacaoProfissionalController {

    private final InformacaoProfissionalService informacaoProfissionalService;

    @Autowired
    public InformacaoProfissionalController(InformacaoProfissionalService informacaoProfissionalService) {
        this.informacaoProfissionalService = informacaoProfissionalService;
    }

    @GetMapping
    @Operation(summary = "Listar todas as informações profissionais")
    public List<InformacaoProfissionalModel> listarTodos() {
        return informacaoProfissionalService.listarTodos();
    }

    @GetMapping("/editar/{id}")
    @Operation(summary = "Buscar informação profissional por ID")
    public InformacaoProfissionalModel listarPorId(@PathVariable UUID id) {
        return informacaoProfissionalService.listarPeloId(id)
                .orElseThrow(() -> new RuntimeException("Informação profissional não encontrada"));
    }

    @GetMapping("/egresso/{cpf}")
    @Operation(summary = "Buscar informações profissionais por CPF do egresso")
    public List<InformacaoProfissionalModel> buscarPorEgressoCpf(@PathVariable String cpf) {
        return informacaoProfissionalService.buscarPorEgressoCpf(cpf);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Criar nova informação profissional",
            description = "Cria e salva uma nova entrada de informação profissional associada a um egresso e à sua informação acadêmica."
    )
    public InformacaoProfissionalModel criarInformacaoProfissional(@RequestBody InformacaoProfissionalDTO dto) {
        return informacaoProfissionalService.criarInformacaoProfissional(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar informação profissional")
    public InformacaoProfissionalModel atualizarInformacaoProfissional(@PathVariable UUID id, @RequestBody InformacaoProfissionalDTO dto) {
        return informacaoProfissionalService.atualizarInformacaoProfissional(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir informação profissional")
    public void excluirInformacaoProfissional(@PathVariable UUID id) {
        informacaoProfissionalService.excluirInformacaoProfissional(id);
    }
}
