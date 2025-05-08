package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.InformacaoProfissionalDTO;
import br.ufu.sistemaegressos.model.InformacaoProfissionalModel;
import br.ufu.sistemaegressos.service.InformacaoProfissionalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InformacaoProfissionalController.class)
public class InformacaoProfissionalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InformacaoProfissionalService informacaoProfissionalService;

    @Autowired
    private ObjectMapper objectMapper;

    private InformacaoProfissionalModel informacaoProfissional;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        informacaoProfissional = new InformacaoProfissionalModel();
        informacaoProfissional.setId(UUID.randomUUID());
        informacaoProfissional.setCompany_name("Company Test");
        informacaoProfissional.setCategory("Category Test");
        informacaoProfissional.setJob_type("Full-time");
        informacaoProfissional.setLocation("Location Test");
        informacaoProfissional.setJob_title("Job Title Test");
        informacaoProfissional.setJob_level("Senior");
        informacaoProfissional.setStart_date(LocalDate.now());
    }

    @Test
    public void testListarTodos() throws Exception {
        when(informacaoProfissionalService.listarTodos()).thenReturn(Arrays.asList(informacaoProfissional));
        mockMvc.perform(get("/api/informacoes/profissionais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].company_name").value("Company Test"));
        verify(informacaoProfissionalService, times(1)).listarTodos();
    }

    @Test
    public void testListarPorId() throws Exception {
        when(informacaoProfissionalService.listarPeloId(informacaoProfissional.getId())).thenReturn(Optional.of(informacaoProfissional));
        mockMvc.perform(get("/api/informacoes/profissionais/editar/" + informacaoProfissional.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company_name").value("Company Test"));
        verify(informacaoProfissionalService, times(1)).listarPeloId(informacaoProfissional.getId());
    }

    @Test
    public void testBuscarPorEgressoCpf() throws Exception {
        when(informacaoProfissionalService.buscarPorEgressoCpf("12345678900")).thenReturn(Arrays.asList(informacaoProfissional));
        mockMvc.perform(get("/api/informacoes/profissionais/egresso/12345678900"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].company_name").value("Company Test"));
        verify(informacaoProfissionalService, times(1)).buscarPorEgressoCpf("12345678900");
    }

    @Test
    public void testCriarInformacaoProfissional() throws Exception {
        InformacaoProfissionalDTO dto = new InformacaoProfissionalDTO();
        dto.setCompany_name("Company Test");
        when(informacaoProfissionalService.criarInformacaoProfissional(any(InformacaoProfissionalDTO.class))).thenReturn(informacaoProfissional);
        mockMvc.perform(post("/api/informacoes/profissionais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.company_name").value("Company Test"));
        verify(informacaoProfissionalService, times(1)).criarInformacaoProfissional(any(InformacaoProfissionalDTO.class));
    }

    @Test
    public void testAtualizarInformacaoProfissional() throws Exception {
        InformacaoProfissionalDTO dto = new InformacaoProfissionalDTO();
        dto.setCompany_name("Updated Company");
        when(informacaoProfissionalService.atualizarInformacaoProfissional(eq(informacaoProfissional.getId()), any(InformacaoProfissionalDTO.class))).thenReturn(informacaoProfissional);
        mockMvc.perform(put("/api/informacoes/profissionais/" + informacaoProfissional.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company_name").value("Company Test"));
        verify(informacaoProfissionalService, times(1)).atualizarInformacaoProfissional(eq(informacaoProfissional.getId()), any(InformacaoProfissionalDTO.class));
    }

    @Test
    public void testExcluirInformacaoProfissional() throws Exception {
        doNothing().when(informacaoProfissionalService).excluirInformacaoProfissional(informacaoProfissional.getId());
        mockMvc.perform(delete("/api/informacoes/profissionais/" + informacaoProfissional.getId()))
                .andExpect(status().isNoContent());
        verify(informacaoProfissionalService, times(1)).excluirInformacaoProfissional(informacaoProfissional.getId());
    }
}