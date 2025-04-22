package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.PublicacaoDTO;
import br.ufu.sistemaegressos.model.PublicacaoModel;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.repository.PublicacaoRepository;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PublicacaoServiceTest {

    @Mock
    private PublicacaoRepository publicacaoRepository;

    @Mock
    private InformacaoAcademicaRepository informacaoAcademicaRepository;

    @InjectMocks
    private PublicacaoService publicacaoService;

    private PublicacaoModel publicacao;
    private InformacaoAcademicaModel informacaoAcademica;
    private PublicacaoDTO publicacaoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        informacaoAcademica = new InformacaoAcademicaModel();
        informacaoAcademica.setId(UUID.randomUUID());

        publicacao = new PublicacaoModel();
        publicacao.setId(UUID.randomUUID());
        publicacao.setTitulo("Título de Teste");
        publicacao.setAutores("Autor de Teste");
        publicacao.setAno_publicacao(2023);
        publicacao.setVeiculo("Veículo de Teste");
        publicacao.setUrl_publicacao("http://teste.com");
        publicacao.setInformacao_academica(informacaoAcademica);

        publicacaoDTO = new PublicacaoDTO();
        publicacaoDTO.setTitulo("Título de Teste");
        publicacaoDTO.setAutores("Autor de Teste");
        publicacaoDTO.setAno_publicacao(2023);
        publicacaoDTO.setVeiculo("Veículo de Teste");
        publicacaoDTO.setUrl_publicacao("http://teste.com");
        publicacaoDTO.setId_informacao_academica(informacaoAcademica.getId());
    }

    @Test
    void testBuscarPorEgresso() {
        when(publicacaoRepository.buscarPublicacaoPorEgresso("12345678900")).thenReturn(Arrays.asList(publicacao));

        List<PublicacaoModel> result = publicacaoService.buscarPorEgresso("12345678900");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Título de Teste", result.get(0).getTitulo());
        verify(publicacaoRepository, times(1)).buscarPublicacaoPorEgresso("12345678900");
    }

    @Test
    void testCriar() {
        when(informacaoAcademicaRepository.findById(publicacaoDTO.getId_informacao_academica())).thenReturn(Optional.of(informacaoAcademica));
        when(publicacaoRepository.save(any(PublicacaoModel.class))).thenReturn(publicacao);

        PublicacaoModel result = publicacaoService.criar(publicacaoDTO);

        assertNotNull(result);
        assertEquals("Título de Teste", result.getTitulo());
        verify(publicacaoRepository, times(1)).save(any(PublicacaoModel.class));
    }

    @Test
    void testAtualizar() {
        when(publicacaoRepository.findById(publicacao.getId())).thenReturn(Optional.of(publicacao));
        when(publicacaoRepository.save(any(PublicacaoModel.class))).thenReturn(publicacao);

        PublicacaoModel result = publicacaoService.atualizar(publicacao.getId(), publicacaoDTO);

        assertNotNull(result);
        assertEquals("Título de Teste", result.getTitulo());
        verify(publicacaoRepository, times(1)).save(any(PublicacaoModel.class));
    }

    @Test
    void testExcluir() {
        doNothing().when(publicacaoRepository).deleteById(publicacao.getId());

        publicacaoService.excluir(publicacao.getId());

        verify(publicacaoRepository, times(1)).deleteById(publicacao.getId());
    }
}