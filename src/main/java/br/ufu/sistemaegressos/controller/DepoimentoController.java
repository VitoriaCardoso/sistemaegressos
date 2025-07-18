package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.service.DepoimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/depoimentos")
@Tag(name = "Depoimentos")
public class DepoimentoController {

    private final DepoimentoService depoimentoService;

    @Autowired
    public DepoimentoController(DepoimentoService depoimentoService) {
        this.depoimentoService = depoimentoService;
    }

    @GetMapping
    @Operation(
            summary = "Listar depoimentos filtrados",
            description = "Retorna os depoimentos com base nos filtros opcionais de campus, semestre letivo, curso e titulação.",
            parameters = {
                    @Parameter(name = "campus", description = "Nome do campus", required = false),
                    @Parameter(name = "semestreLetivo", description = "Semestre letivo (ex: 2023/2)", required = false),
                    @Parameter(name = "curso", description = "Nome do curso", required = false),
                    @Parameter(name = "titulacao", description = "Titulação (ex: Graduação, Mestrado)", required = false)
            }
    )
    public List<DepoimentoModel> buscarTodosDepoimentos(
            @RequestParam(required = false) String campus,
            @RequestParam(required = false) String semestreLetivo,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) String titulacao
    ) {
        return depoimentoService.listarTodos(campus, semestreLetivo, curso, titulacao);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar depoimento por ID")
    public ResponseEntity<DepoimentoModel> buscarDepoimentoPeloID(@PathVariable UUID id) {
        Optional<DepoimentoModel> depoimento = depoimentoService.listarPeloId(id);
        return depoimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar novo depoimento")
    public DepoimentoModel criarDepoimento(@RequestBody DepoimentoDTO depoimentoDTO) {
        return depoimentoService.criarDepoimento(depoimentoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir depoimento")
    public ResponseEntity<Void> excluirDepoimento(@PathVariable UUID id) {
        depoimentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/egresso/{cpf}")
    @Operation(summary = "Listar depoimentos de um egresso")
    public List<DepoimentoModel> listarPorCpfEgresso(@PathVariable String cpf) {
        return depoimentoService.listarPorCpfEgresso(cpf);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar depoimento")
    public ResponseEntity<DepoimentoModel> atualizar(@PathVariable UUID id, @RequestBody DepoimentoDTO dto) {
        DepoimentoModel atualizado = depoimentoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}