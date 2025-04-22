package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.service.DepoimentoService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepoimentoController.class)
public class DepoimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepoimentoService depoimentoService;

    @Autowired
    private ObjectMapper objectMapper;

    private DepoimentoModel depoimento;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        depoimento = new DepoimentoModel();
        depoimento.setId(UUID.fromString("1639e15f-4c29-42e2-b148-300e7e479643"));
        depoimento.setTexto_depoimento("Depoimento de teste");
        depoimento.setData_cadastro(LocalDate.now());
    }

    @Test
    public void testBuscarTodosDepoimentos() throws Exception {
        when(depoimentoService.listarTodos(any(), any(), any(), any())).thenReturn(Arrays.asList(depoimento));
        mockMvc.perform(get("/api/depoimentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1639e15f-4c29-42e2-b148-300e7e479643"))
                .andExpect(jsonPath("$[0].texto_depoimento").value("Depoimento de teste"));
        verify(depoimentoService, times(1)).listarTodos(any(), any(), any(), any());
    }

    @Test
    public void testBuscarDepoimentoPeloID() throws Exception {
        when(depoimentoService.listarPeloId(UUID.fromString("1639e15f-4c29-42e2-b148-300e7e479643"))).thenReturn(Optional.of(depoimento));

        mockMvc.perform(get("/api/depoimentos/1639e15f-4c29-42e2-b148-300e7e479643"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1639e15f-4c29-42e2-b148-300e7e479643"))
                .andExpect(jsonPath("$.texto_depoimento").value("Depoimento de teste"));

        verify(depoimentoService, times(1)).listarPeloId(UUID.fromString("1639e15f-4c29-42e2-b148-300e7e479643"));
    }

    @Test
    public void testCriarDepoimento() throws Exception {
        DepoimentoDTO depoimentoDTO = new DepoimentoDTO();
        depoimentoDTO.setTexto_depoimento("Depoimento de teste");

        when(depoimentoService.criarDepoimento(any(DepoimentoDTO.class))).thenReturn(depoimento);

        mockMvc.perform(post("/api/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(depoimentoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1639e15f-4c29-42e2-b148-300e7e479643"))
                .andExpect(jsonPath("$.texto_depoimento").value("Depoimento de teste"));

        verify(depoimentoService, times(1)).criarDepoimento(any(DepoimentoDTO.class));
    }

    @Test
    public void testExcluirDepoimento() throws Exception {
        doNothing().when(depoimentoService).excluir(UUID.fromString("1639e15f-4c29-42e2-b148-300e7e479643"));

        mockMvc.perform(delete("/api/depoimentos/1639e15f-4c29-42e2-b148-300e7e479643"))
                .andExpect(status().isNoContent());
        verify(depoimentoService, times(1)).excluir(UUID.fromString("1639e15f-4c29-42e2-b148-300e7e479643"));
    }

    @Test
    public void testListarPorCpfEgresso() throws Exception {
        when(depoimentoService.listarPorCpfEgresso("12345678910")).thenReturn(Arrays.asList(depoimento));
        mockMvc.perform(get("/api/depoimentos/egresso/12345678910"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1639e15f-4c29-42e2-b148-300e7e479643"))
                .andExpect(jsonPath("$[0].texto_depoimento").value("Depoimento de teste"));
        verify(depoimentoService, times(1)).listarPorCpfEgresso(anyString());
    }

    @Test
    public void testAtualizar() throws Exception {
        DepoimentoDTO dto = new DepoimentoDTO();
        dto.setTexto_depoimento("Depoimento atualizado");
        when(depoimentoService.atualizar(any(UUID.class), any(DepoimentoDTO.class))).thenReturn(depoimento);
        mockMvc.perform(put("/api/depoimentos/1639e15f-4c29-42e2-b148-300e7e479643")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1639e15f-4c29-42e2-b148-300e7e479643"))
                .andExpect(jsonPath("$.texto_depoimento").value("Depoimento de teste"));
        verify(depoimentoService, times(1)).atualizar(any(UUID.class), any(DepoimentoDTO.class));
    }
}