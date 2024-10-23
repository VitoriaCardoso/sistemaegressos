package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.EgressoAtualizarDTO;
import br.ufu.sistemaegressos.dto.EgressoCriarDTO;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.service.EgressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/egressos")
public class EgressoController {

    private final EgressoService egressoService;

    @Autowired
    public EgressoController (EgressoService egressoService){
        this.egressoService = egressoService;
    }

    @GetMapping
    public List<EgressoModel> buscarTodosEgressos() {
        return egressoService.buscarTodos();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<EgressoModel> buscarEgressoPorCPF(@PathVariable String cpf) {
        Optional<EgressoModel> egresso = egressoService.buscarPeloCPF(cpf);
        return egresso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EgressoModel criarEgresso(@RequestBody EgressoCriarDTO egressoDTO) {
        return egressoService.criar(egressoDTO);
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<EgressoModel> atualizarEgresso(@PathVariable String cpf, @RequestBody EgressoAtualizarDTO egressoDTO) {
        EgressoModel egressoAtualizado = egressoService.atualizar(cpf, egressoDTO);
        if (egressoAtualizado != null) {
            return ResponseEntity.ok(egressoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluirEgresso(@PathVariable String cpf) {
        egressoService.excluir(cpf);
        return ResponseEntity.noContent().build();
    }
}
