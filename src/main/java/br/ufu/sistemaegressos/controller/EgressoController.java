package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.EgressoAtualizarDTO;
import br.ufu.sistemaegressos.dto.EgressoCriarDTO;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.service.EgressoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/egressos")
@Tag(name = "Egressos")
public class EgressoController {

    private final EgressoService egressoService;

    @Autowired
    public EgressoController (EgressoService egressoService){
        this.egressoService = egressoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os egressos")
    public List<EgressoModel> buscarTodosEgressos() {
        return egressoService.buscarTodos();
    }

    @GetMapping("/{cpf}")
    @Operation(
            summary = "Buscar egresso por CPF",
            description = "Retorna os dados do egresso correspondente ao CPF informado. Retorna 404 se não encontrado."
    )
    public ResponseEntity<EgressoModel> buscarEgressoPorCPF(@PathVariable String cpf) {
        Optional<EgressoModel> egresso = egressoService.buscarPeloCPF(cpf);
        return egresso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar um novo egresso")
    public EgressoModel criarEgresso(@RequestBody EgressoCriarDTO egressoDTO) {
        return egressoService.criar(egressoDTO);
    }

    @PutMapping("/{cpf}")
    @Operation(
            summary = "Atualizar dados de um egresso",
            description = "Atualiza os dados do egresso identificado pelo CPF. Retorna 404 se o egresso não for encontrado."
    )
    public ResponseEntity<EgressoModel> atualizarEgresso(@PathVariable String cpf, @RequestBody EgressoAtualizarDTO egressoDTO) {
        EgressoModel egressoAtualizado = egressoService.atualizar(cpf, egressoDTO);
        if (egressoAtualizado != null) {
            return ResponseEntity.ok(egressoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cpf}")
    @Operation(
            summary = "Excluir um egresso",
            description = "Remove o egresso do sistema com base no CPF informado. Retorna 204 em caso de sucesso."
    )
    public ResponseEntity<Void> excluirEgresso(@PathVariable String cpf) {
        egressoService.excluir(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtro")
    @Operation(
            summary = "Buscar egressos por filtros",
            description = "Permite buscar egressos usando múltiplos filtros opcionais como nome, CPF, campus, curso, titulação e datas de ingresso/conclusão."
    )
    public List<EgressoModel> buscarEgressoPorFiltro(@RequestParam(required = false) String nome,
                                                     @RequestParam(required = false) String cpf,
                                                     @RequestParam(required = false) String campus,
                                                     @RequestParam(required = false) String curso,
                                                     @RequestParam(required = false) String titulacao,
                                                     @RequestParam(required = false) LocalDate dataIngresso,
                                                     @RequestParam(required = false) LocalDate dataConclusao) {
        return egressoService.buscarPorFiltro(nome, cpf, campus, curso, titulacao, dataIngresso, dataConclusao);
    }
}
