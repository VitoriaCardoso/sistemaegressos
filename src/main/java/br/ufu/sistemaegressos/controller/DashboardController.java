package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.CursoCampusTitulacaoDTO;
import br.ufu.sistemaegressos.dto.EgressoDepoimentoDTO;
import br.ufu.sistemaegressos.dto.StatusEstudanteDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
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
    public Long getTotalEstudantesAtivos() {
        return dashboardService.buscarTotalEstudantesAtivos();
    }

    @GetMapping("/estudantes/inativos")
    public Long getTotalEstudantesInativos() {
        return dashboardService.buscarTotalEstudantesInativos();
    }

    @GetMapping("/estudantes/campus/total")
    public Map<String, Long> getTotalPorCampus() {
        return dashboardService.buscarTotalPorCampus();
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
