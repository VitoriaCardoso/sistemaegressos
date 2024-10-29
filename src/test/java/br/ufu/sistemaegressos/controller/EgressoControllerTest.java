package br.ufu.sistemaegressos.controller;

import static org.junit.jupiter.api.Assertions.*;
import br.ufu.sistemaegressos.dto.EgressoAtualizarDTO;
import br.ufu.sistemaegressos.dto.EgressoCriarDTO;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.service.EgressoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EgressoController.class)
public class EgressoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EgressoService egressoService;

    @Autowired
    private ObjectMapper objectMapper;

    private EgressoModel egresso;

    @BeforeEach
    public void setup() {
        egresso = new EgressoModel();
        egresso.setCpf("12345678900");
        egresso.setNome("João Silva");
    }

    @Test
    public void testBuscarTodosEgressos() throws Exception {
        when(egressoService.buscarTodos()).thenReturn(Arrays.asList(egresso));

        mockMvc.perform(get("/api/egressos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cpf").value("12345678900"))
                .andExpect(jsonPath("$[0].nome").value("João Silva"));
    }

    @Test
    public void testBuscarEgressoPorCPF() throws Exception {
        when(egressoService.buscarPeloCPF("12345678900")).thenReturn(Optional.of(egresso));

        mockMvc.perform(get("/api/egressos/12345678900"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.nome").value("João Silva"));
    }

    @Test
    public void testCriarEgresso() throws Exception {
        EgressoCriarDTO criarDTO = new EgressoCriarDTO();
        when(egressoService.criar(any(EgressoCriarDTO.class))).thenReturn(egresso);

        mockMvc.perform(post("/api/egressos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(criarDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.nome").value("João Silva"));
    }

    @Test
    public void testAtualizarEgresso() throws Exception {
        EgressoAtualizarDTO atualizarDTO = new EgressoAtualizarDTO();
        egresso.setNome("João Silva Atualizado");
        when(egressoService.atualizar(eq("12345678900"), any(EgressoAtualizarDTO.class))).thenReturn(egresso);

        mockMvc.perform(patch("/api/egressos/12345678900")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizarDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.nome").value("João Silva Atualizado"));
    }

    @Test
    public void testExcluirEgresso() throws Exception {
        doNothing().when(egressoService).excluir("12345678900");

        mockMvc.perform(delete("/api/egressos/12345678900"))
                .andExpect(status().isNoContent());
    }
}

