package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.EgressoAtualizarDTO;
import br.ufu.sistemaegressos.dto.EgressoCriarDTO;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.service.EgressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PutMapping("/{cpf}")
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

    @GetMapping("/filtro")
    public List<EgressoModel> buscarEgressoPorFiltro(@RequestParam(required = false) String nome,
                                                     @RequestParam(required = false) String cpf,
                                                     @RequestParam(required = false) String campus,
                                                     @RequestParam(required = false) String nomeCurso,
                                                     @RequestParam(required = false) String codigoCurso,
                                                     @RequestParam(required = false) String titulacao,
                                                     @RequestParam(required = false) LocalDate dataIngresso,
                                                     @RequestParam(required = false) LocalDate dataConclusao) {
        return egressoService.buscarPorFiltro(nome, cpf, campus, nomeCurso, codigoCurso, titulacao, dataIngresso, dataConclusao);
    }
}
