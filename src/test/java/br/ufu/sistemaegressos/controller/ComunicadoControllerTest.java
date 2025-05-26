package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.ComunicadoDTO;
import br.ufu.sistemaegressos.model.ComunicadoModel;
import br.ufu.sistemaegressos.service.ComunicadoService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ComunicadoController.class)
public class ComunicadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComunicadoService comunicadoService;

    @Autowired
    private ObjectMapper objectMapper;

    private ComunicadoModel comunicado;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        comunicado = new ComunicadoModel();
        comunicado.setId(UUID.fromString("89ec4283-e1fa-433f-b1ca-819d1e1d235f"));
        comunicado.setTitulo("Título do Comunicado");
    }

    @Test
    public void testListarTodos() throws Exception {
        when(comunicadoService.listarTodos()).thenReturn(Arrays.asList(comunicado));

        mockMvc.perform(get("/api/comunicados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("89ec4283-e1fa-433f-b1ca-819d1e1d235f"))
                .andExpect(jsonPath("$[0].titulo").value("Título do Comunicado"));
    }

    @Test
    public void testBuscarPorFiltro() throws Exception {
        when(comunicadoService.buscarPorFiltro("Curso A", "Graduação", true))
                .thenReturn(Arrays.asList(comunicado));

        mockMvc.perform(get("/api/comunicados/filtro")
                        .param("cursoDestino", "Curso A")
                        .param("nivelCursoDestino", "Graduação")
                        .param("paraTodos", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("89ec4283-e1fa-433f-b1ca-819d1e1d235f"))
                .andExpect(jsonPath("$[0].titulo").value("Título do Comunicado"));
    }

    @Test
    public void testCriarComunicado() throws Exception {
        ComunicadoDTO comunicadoDTO = new ComunicadoDTO();
        comunicadoDTO.setTitulo("Novo Comunicado");

        when(comunicadoService.criarComunicado(any(ComunicadoDTO.class))).thenReturn(comunicado);

        mockMvc.perform(post("/api/comunicados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comunicadoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("89ec4283-e1fa-433f-b1ca-819d1e1d235f"))
                .andExpect(jsonPath("$.titulo").value("Título do Comunicado"));
    }
}