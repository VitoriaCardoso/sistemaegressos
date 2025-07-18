package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.CursoCampusTitulacaoDTO;
import br.ufu.sistemaegressos.dto.EgressoDepoimentoDTO;
import br.ufu.sistemaegressos.dto.StatusEstudanteDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/estudantes/status")
    public List<StatusEstudanteDTO> buscarStatusEstudantes() {
        return dashboardService.buscarStatusEstudantesComTotal();
    }

    @GetMapping("/estudantes/nivel")
    public List<StatusEstudanteDTO> buscarNivelEstudantes() {
        return dashboardService.buscarNivelEstudantes();
    }

    @GetMapping("/estudantes/campus")
    public List<StatusEstudanteDTO> buscarCampusEstudantes() {
        return dashboardService.buscarCampusEstudantes();
    }

    @GetMapping("/estudantes/ativos")
    @Operation(
            summary = "Total de estudantes ativos",
            description = "Retorna o número total de estudantes ativos registrados no sistema."
    )
    public Long getTotalEstudantesAtivos() {
        return dashboardService.buscarTotalEstudantesAtivos();
    }

    @GetMapping("/estudantes/inativos")
    @Operation(
            summary = "Total de estudantes inativos",
            description = "Retorna o número total de estudantes inativos registrados no sistema."
    )
    public Long getTotalEstudantesInativos() {
        return dashboardService.buscarTotalEstudantesInativos();
    }

    @GetMapping("/estudantes/campus/total")
    @Operation(
            summary = "Total de estudantes por campus",
            description = "Agrupa e retorna o total de estudantes por campus."
    )
    public Map<String, Long> getTotalPorCampus() {
        return dashboardService.buscarTotalPorCampus();
    }

    @GetMapping("/estudantes/titulacao")
    @Operation(
            summary = "Total de estudantes por titulação",
            description = "Agrupa e retorna o total de estudantes por nível de titulação (ex: graduação, mestrado, doutorado)."
    )
    public Map<String, Long> getTotalPorTitulacao() {
        return dashboardService.buscarTotalPorTitulacao();
    }

    @GetMapping("/estudantes/curso-campus-titulacao")
    public List<CursoCampusTitulacaoDTO> getCursoCampusTitulacao(
            @RequestParam(required = false) String campus,
            @RequestParam(required = false) String semestre,
            @RequestParam(required = false) String titulacao,
            @RequestParam(required = false) String curso) {

        return dashboardService.buscarTotalPorCursoCampusTitulacao(campus, semestre, titulacao, curso);
    }

    @GetMapping("/depoimentos")
    public List<EgressoDepoimentoDTO> listar() {
        return dashboardService.listarDepoimentos();
    }
}
