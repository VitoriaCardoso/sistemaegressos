package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.CursoNameDTO;
import br.ufu.sistemaegressos.dto.InformacaoAcademicaDTO;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.service.InformacaoAcademicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/informacoes/academicas")
@Tag(name = "Informações Acadêmicas")
@CrossOrigin("http://localhost:4200")
public class InformacaoAcademicaController {

    private final InformacaoAcademicaService informacaoAcademicaService;

    @Autowired
    public InformacaoAcademicaController(InformacaoAcademicaService informacaoAcademicaService) {
        this.informacaoAcademicaService = informacaoAcademicaService;
    }

    @GetMapping("/egresso/{cpf}")
    @Operation(
            summary = "Buscar informações acadêmicas por CPF do egresso",
            description = "Retorna todas as informações acadêmicas associadas ao CPF de um egresso."
    )
    public List<InformacaoAcademicaModel> buscarPorEgresso(@PathVariable String cpf) {
        return informacaoAcademicaService.buscarPorEgresso(cpf);
    }

    @GetMapping("/cursos/{cpf}")
    @Operation(
            summary = "Listar cursos de um egresso",
            description = "Retorna os nomes dos cursos (com ID) associados a um egresso, usando o CPF como referência."
    )
    public List<CursoNameDTO> buscarCursosPorCpf(@PathVariable String cpf) {
        return informacaoAcademicaService.buscarCursosPorCpf(cpf);
    }

    @GetMapping("/editar/{id}")
    @Operation(
            summary = "Buscar informações acadêmicas por ID",
            description = "Retorna as informações acadêmicas correspondentes ao ID informado. Usado para carregamento em edição."
    )
    public List<InformacaoAcademicaModel> buscarPorInformacaoAcademica(@PathVariable UUID id) {
        System.out.println("Dados enviados para o frontend: " + informacaoAcademicaService.buscarPorInformacaoAcademica(id));
        return informacaoAcademicaService.buscarPorInformacaoAcademica(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar nova informação acadêmica")
    public InformacaoAcademicaModel criarInformacaoAcademica(@RequestBody InformacaoAcademicaDTO dto) {
        return informacaoAcademicaService.criar(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar informação acadêmica")
    public InformacaoAcademicaModel atualizarInformacaoAcademica(@PathVariable UUID id, @RequestBody InformacaoAcademicaDTO dto) {
        return informacaoAcademicaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir informação acadêmica")
    public void excluirInformacaoAcademica(@PathVariable UUID id) {
        informacaoAcademicaService.excluir(id);
    }

    @GetMapping("/sem-depoimento/{cpf}")
    @Operation(
            summary = "Listar informações acadêmicas sem depoimento",
            description = "Retorna informações acadêmicas do egresso (com base no CPF) que ainda não estão associadas a um depoimento."
    )
    public List<InformacaoAcademicaModel> buscarInformacoesAcademicasSemDepoimento(@PathVariable String cpf) {
        return informacaoAcademicaService.buscarInformacoesAcademicasSemDepoimento(cpf);
    }
}
