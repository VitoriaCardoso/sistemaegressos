package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.InformacaoAcademicaDTO;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.service.InformacaoAcademicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InformacaoAcademicaController.class)
public class InformacaoAcademicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InformacaoAcademicaService informacaoAcademicaService;

    @Autowired
    private ObjectMapper objectMapper;

    private InformacaoAcademicaModel informacaoAcademica;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        informacaoAcademica = new InformacaoAcademicaModel();
        informacaoAcademica.setId(UUID.randomUUID());
        informacaoAcademica.setCourse_name("Curso Teste");
    }

    @Test
    public void testBuscarPorEgresso() throws Exception {
        when(informacaoAcademicaService.buscarPorEgresso("12345678900")).thenReturn(Arrays.asList(informacaoAcademica));
        mockMvc.perform(get("/api/informacoes/academicas/egresso/12345678900"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].course_name").value("Curso Teste"));
        verify(informacaoAcademicaService, times(1)).buscarPorEgresso("12345678900");
    }

    @Test
    public void testBuscarPorInformacaoAcademica() throws Exception {
        when(informacaoAcademicaService.buscarPorInformacaoAcademica(informacaoAcademica.getId())).thenReturn(Arrays.asList(informacaoAcademica));
        mockMvc.perform(get("/api/informacoes/academicas/editar/" + informacaoAcademica.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].course_name").value("Curso Teste"));
        verify(informacaoAcademicaService, times(1)).buscarPorInformacaoAcademica(informacaoAcademica.getId());
    }

    @Test
    public void testCriarInformacaoAcademica() throws Exception {
        InformacaoAcademicaDTO dto = new InformacaoAcademicaDTO();
        dto.setCourse_name("Curso Teste");
        when(informacaoAcademicaService.criar(any(InformacaoAcademicaDTO.class))).thenReturn(informacaoAcademica);
        mockMvc.perform(post("/api/informacoes/academicas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.course_name").value("Curso Teste"));
        verify(informacaoAcademicaService, times(1)).criar(any(InformacaoAcademicaDTO.class));
    }

    @Test
    public void testAtualizarInformacaoAcademica() throws Exception {
        InformacaoAcademicaDTO dto = new InformacaoAcademicaDTO();
        dto.setCourse_name("Curso Atualizado");
        when(informacaoAcademicaService.atualizar(eq(informacaoAcademica.getId()), any(InformacaoAcademicaDTO.class))).thenReturn(informacaoAcademica);
        mockMvc.perform(put("/api/informacoes/academicas/" + informacaoAcademica.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.course_name").value("Curso Teste"));
        verify(informacaoAcademicaService, times(1)).atualizar(eq(informacaoAcademica.getId()), any(InformacaoAcademicaDTO.class));
    }

    @Test
    public void testExcluirInformacaoAcademica() throws Exception {
        doNothing().when(informacaoAcademicaService).excluir(informacaoAcademica.getId());
        mockMvc.perform(delete("/api/informacoes/academicas/" + informacaoAcademica.getId()))
                .andExpect(status().isNoContent());
        verify(informacaoAcademicaService, times(1)).excluir(informacaoAcademica.getId());
    }
}