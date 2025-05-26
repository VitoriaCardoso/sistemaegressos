package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.service.DashboardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DashboardController.class)
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DashboardService dashboardService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTotalEstudantesAtivos() throws Exception {
        when(dashboardService.buscarTotalEstudantesAtivos()).thenReturn(100L);

        mockMvc.perform(get("/api/dashboard/estudantes/ativos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(100L));
    }

    @Test
    public void testGetTotalEstudantesInativos() throws Exception {
        when(dashboardService.buscarTotalEstudantesInativos()).thenReturn(50L);

        mockMvc.perform(get("/api/dashboard/estudantes/inativos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(50L));
    }

    @Test
    public void testGetTotalPorCampus() throws Exception {
        Map<String, Long> totalPorCampus = Map.of("Campus A", 30L, "Campus B", 70L);
        when(dashboardService.buscarTotalPorCampus()).thenReturn(totalPorCampus);

        mockMvc.perform(get("/api/dashboard/estudantes/campus"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Campus A']").value(30L))
                .andExpect(jsonPath("$.['Campus B']").value(70L));
    }

    @Test
    public void testGetTotalPorTitulacao() throws Exception {
        Map<String, Long> totalPorTitulacao = Map.of("Mestrado", 40L, "Doutorado", 60L);
        when(dashboardService.buscarTotalPorTitulacao()).thenReturn(totalPorTitulacao);

        mockMvc.perform(get("/api/dashboard/estudantes/titulacao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.['Mestrado']").value(40L))
                .andExpect(jsonPath("$.['Doutorado']").value(60L));
    }

    @Test
    public void testListarDepoimentos() throws Exception {
        DepoimentoModel depoimento = new DepoimentoModel();
        depoimento.setTexto_depoimento("Depoimento de teste");
        List<DepoimentoModel> depoimentos = Arrays.asList(depoimento);

        when(dashboardService.listarDepoimentos("Campus A", "2023/2", "Curso X", "Mestrado")).thenReturn(depoimentos);

        mockMvc.perform(get("/api/dashboard/depoimentos")
                        .param("campus", "Campus A")
                        .param("semestreLetivo", "2023/2")
                        .param("curso", "Curso X")
                        .param("titulacao", "Mestrado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].texto_depoimento").value("Depoimento de teste"));
    }
}