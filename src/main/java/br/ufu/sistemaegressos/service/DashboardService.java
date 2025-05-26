package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.*;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService{

    @Autowired
    private EgressoRepository egressoRepository;

    @Autowired
    private DepoimentoService depoimentoService;

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private ComunicadoRepository comunicadoRepository;

    @Autowired
    private InformacaoAcademicaRepository informacaoAcademicaRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    public List<EgressoDepoimentoDTO> listarDepoimentos() {
        return depoimentoRepository.buscarDepoimentos();
    }

    public List<StatusEstudanteDTO> buscarStatusEstudantesComTotal() {
        return dashboardRepository.buscarStatusEstudantesComTotal();
    }

    public List<StatusEstudanteDTO> buscarNivelEstudantes() {
        return dashboardRepository.buscarLevelEstudantesComTotal();
    }

    public List<StatusEstudanteDTO> buscarCampusEstudantes() {
        return dashboardRepository.buscarCampusEstudantesComTotal();
    }

    public EstatisticasDTO buscarEstatisticas(String campus, String semestreLetivo, String curso, String titulacao) {
        long totalEgressos = egressoRepository.count();
        List<DepoimentoModel> totalDepoimentos = depoimentoService.listarTodos(campus, semestreLetivo, curso, titulacao);

        return new EstatisticasDTO(totalEgressos, totalDepoimentos);
    }

    public Long buscarTotalEstudantesAtivos() {
        return informacaoAcademicaRepository.contarEstudantesAtivos();
    }

    public Long buscarTotalEstudantesInativos() {
        return informacaoAcademicaRepository.contarEstudantesInativos();
    }

    public List<CursoCampusTitulacaoDTO> buscarTotalPorCursoCampusTitulacao(
            String campus,
            String semestre,
            String titulacao,
            String curso
    ) {
        return dashboardRepository.buscarTotalPorCursoCampusTitulacao(campus, semestre, titulacao, curso);
    }

    public Map<String, Long> buscarTotalPorCampus() {
        List<Object[]> resultado = informacaoAcademicaRepository.contarEstudantesPorCampus();
        Map<String, Long> totalPorCampus = new HashMap<>();
        for (Object[] row : resultado) {
            totalPorCampus.put((String) row[0], (Long) row[1]);
        }
        return totalPorCampus;
    }

    public Map<String, Long> buscarTotalPorTitulacao() {
        List<Object[]> resultado = informacaoAcademicaRepository.contarEstudantesPorTitulacao();
        Map<String, Long> totalPorTitulacao = new HashMap<>();
        for (Object[] row : resultado) {
            totalPorTitulacao.put((String) row[0], (Long) row[1]);
        }
        return totalPorTitulacao;
    }

    public List<DepoimentoModel> listarDepoimentos(String campus, String semestreLetivo, String curso, String titulacao) {
        return depoimentoService.listarTodos(campus,semestreLetivo,curso,titulacao);
    }
}
