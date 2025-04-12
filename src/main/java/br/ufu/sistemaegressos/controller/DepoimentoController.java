package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.service.DepoimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/depoimentos")
public class DepoimentoController {

    private final DepoimentoService depoimentoService;

    @Autowired
    public DepoimentoController(DepoimentoService depoimentoService) {
        this.depoimentoService = depoimentoService;
    }

    @GetMapping
    public List<DepoimentoModel> buscarTodosDepoimentos(String campus, Integer totalEstudantes, String curso, String titulacao) {
        return depoimentoService.listarTodos(campus,totalEstudantes,curso,titulacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepoimentoModel> buscarDepoimentoPeloID(@PathVariable UUID id) {
        Optional<DepoimentoModel> depoimento = depoimentoService.listarPeloId(id);
        return depoimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepoimentoModel criarDepoimento(@RequestBody DepoimentoDTO depoimentoDTO) {
        return depoimentoService.criarDepoimento(depoimentoDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirDepoimento(@PathVariable UUID id) {
        depoimentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/egresso/{cpf}")
    public List<DepoimentoModel> listarPorCpfEgresso(@PathVariable String cpf) {
        return depoimentoService.listarPorCpfEgresso(cpf);
    }

}