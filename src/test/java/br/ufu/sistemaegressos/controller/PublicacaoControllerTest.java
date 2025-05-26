package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.PublicacaoDTO;
import br.ufu.sistemaegressos.model.PublicacaoModel;
import br.ufu.sistemaegressos.service.PublicacaoService;
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

@WebMvcTest(PublicacaoController.class)
public class PublicacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicacaoService publicacaoService;

    @Autowired
    private ObjectMapper objectMapper;

    private PublicacaoModel publicacao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        publicacao = new PublicacaoModel();
        publicacao.setId(UUID.randomUUID());
        publicacao.setTitulo("Titulo Teste");
        publicacao.setAutores("Autor Teste");
        publicacao.setAno_publicacao(2023);
        publicacao.setVeiculo("Veiculo Teste");
        publicacao.setUrl_publicacao("http://example.com");
    }

    @Test
    public void testBuscarPublicacoesPorEgresso() throws Exception {
        when(publicacaoService.buscarPorEgresso("12345678900")).thenReturn(Arrays.asList(publicacao));
        mockMvc.perform(get("/api/publicacoes/egresso/12345678900"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Titulo Teste"));
        verify(publicacaoService, times(1)).buscarPorEgresso("12345678900");
    }

    @Test
    public void testCriarPublicacao() throws Exception {
        PublicacaoDTO publicacaoDTO = new PublicacaoDTO();
        publicacaoDTO.setTitulo("Titulo Teste");
        when(publicacaoService.criar(any(PublicacaoDTO.class))).thenReturn(publicacao);
        mockMvc.perform(post("/api/publicacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publicacaoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Titulo Teste"));
        verify(publicacaoService, times(1)).criar(any(PublicacaoDTO.class));
    }

    @Test
    public void testAtualizarPublicacao() throws Exception {
        PublicacaoDTO publicacaoDTO = new PublicacaoDTO();
        publicacaoDTO.setTitulo("Titulo Atualizado");
        when(publicacaoService.atualizar(eq(publicacao.getId()), any(PublicacaoDTO.class))).thenReturn(publicacao);
        mockMvc.perform(put("/api/publicacoes/" + publicacao.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publicacaoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Titulo Teste"));
        verify(publicacaoService, times(1)).atualizar(eq(publicacao.getId()), any(PublicacaoDTO.class));
    }

    @Test
    public void testExcluirPublicacao() throws Exception {
        doNothing().when(publicacaoService).excluir(publicacao.getId());
        mockMvc.perform(delete("/api/publicacoes/" + publicacao.getId()))
                .andExpect(status().isNoContent());
        verify(publicacaoService, times(1)).excluir(publicacao.getId());
    }
}