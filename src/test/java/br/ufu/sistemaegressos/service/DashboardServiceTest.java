package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.EstatisticasDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardServiceTest {

    @Mock
    private EgressoRepository egressoRepository;

    @Mock
    private DepoimentoService depoimentoService;

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Mock
    private ComunicadoRepository comunicadoRepository;

    @Mock
    private InformacaoAcademicaRepository informacaoAcademicaRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarEstatisticas() {
        when(egressoRepository.count()).thenReturn(100L);
        List<DepoimentoModel> depoimentos = Arrays.asList(new DepoimentoModel());
        when(depoimentoService.listarTodos(anyString(), anyString(), anyString(), anyString())).thenReturn(depoimentos);

        EstatisticasDTO estatisticas = dashboardService.buscarEstatisticas("campus", "semestre", "curso", "titulacao");

        assertEquals(100L, estatisticas.getTotalEgressos());
        assertEquals(depoimentos, estatisticas.getTotalDepoimentos());
    }

    @Test
    void testBuscarTotalEstudantesAtivos() {
        when(informacaoAcademicaRepository.contarEstudantesAtivos()).thenReturn(50L);

        Long totalAtivos = dashboardService.buscarTotalEstudantesAtivos();

        assertEquals(50L, totalAtivos);
    }

    @Test
    void testBuscarTotalEstudantesInativos() {
        when(informacaoAcademicaRepository.contarEstudantesInativos()).thenReturn(30L);

        Long totalInativos = dashboardService.buscarTotalEstudantesInativos();

        assertEquals(30L, totalInativos);
    }

    @Test
    void testBuscarTotalPorCampus() {
        List<Object[]> resultado = new ArrayList<>();
        resultado.add(new Object[]{"Campus A", 20L});
        resultado.add(new Object[]{"Campus B", 30L});
        when(informacaoAcademicaRepository.contarEstudantesPorCampus()).thenReturn(resultado);

        Map<String, Long> totalPorCampus = dashboardService.buscarTotalPorCampus();

        assertEquals(2, totalPorCampus.size());
        assertEquals(20L, totalPorCampus.get("Campus A"));
        assertEquals(30L, totalPorCampus.get("Campus B"));
    }

    @Test
    void testBuscarTotalPorTitulacao() {
        List<Object[]> resultado = new ArrayList<>();
        resultado.add(new Object[]{"Mestrado", 15L});
        resultado.add(new Object[]{"Doutorado", 25L});
        when(informacaoAcademicaRepository.contarEstudantesPorTitulacao()).thenReturn(resultado);

        Map<String, Long> totalPorTitulacao = dashboardService.buscarTotalPorTitulacao();

        assertEquals(2, totalPorTitulacao.size());
        assertEquals(15L, totalPorTitulacao.get("Mestrado"));
        assertEquals(25L, totalPorTitulacao.get("Doutorado"));
    }

    @Test
    void testListarDepoimentos() {
        List<DepoimentoModel> depoimentos = Arrays.asList(new DepoimentoModel());
        when(depoimentoService.listarTodos(anyString(), anyString(), anyString(), anyString())).thenReturn(depoimentos);

        List<DepoimentoModel> result = dashboardService.listarDepoimentos("campus", "semestre", "curso", "titulacao");

        assertEquals(depoimentos, result);
    }
}