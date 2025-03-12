package br.ufu.sistemaegressos.controller;

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

    @GetMapping("/estudantes/ativos")
    public Long getTotalEstudantesAtivos() {
        return dashboardService.buscarTotalEstudantesAtivos();
    }

    @GetMapping("/estudantes/inativos")
    public Long getTotalEstudantesInativos() {
        return dashboardService.buscarTotalEstudantesInativos();
    }

    @GetMapping("/estudantes/campus")
    public Map<String, Long> getTotalPorCampus() {
        return dashboardService.buscarTotalPorCampus();
    }

    @GetMapping("/estudantes/titulacao")
    public Map<String, Long> getTotalPorTitulacao() {
        return dashboardService.buscarTotalPorTitulacao();
    }

    @GetMapping("/depoimentos")
    public List<DepoimentoModel> listarDepoimentos(
            @RequestParam(required = false) String campus,
            @RequestParam(required = false) Integer totalEstudantes,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) String titulacao) {

        return dashboardService.listarDepoimentos(campus, totalEstudantes, curso, titulacao);
    }
}
