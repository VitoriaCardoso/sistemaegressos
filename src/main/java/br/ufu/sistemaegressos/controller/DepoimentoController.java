package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.dto.EgressoCriarDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.repository.DepoimentoRepository;
import br.ufu.sistemaegressos.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/depoimentos")
public class DepoimentoController {

    private final DepoimentoService depoimentoService;

    @Autowired
    public DepoimentoController(DepoimentoService depoimentoService) {
        this.depoimentoService = depoimentoService;
    }

    @GetMapping
    public List<DepoimentoModel> buscarTodosDepoimentos() {
        return depoimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepoimentoModel> buscarDepoimentoPeloID(@PathVariable String id) {
        Optional<DepoimentoModel> depoimento = depoimentoService.listarPeloId(id);
        return depoimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DepoimentoModel criarDepoimento(@RequestBody DepoimentoDTO depoimentoDTO) {
        return depoimentoService.criarDepoimento(depoimentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDepoimento(@PathVariable String id) {
        depoimentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}