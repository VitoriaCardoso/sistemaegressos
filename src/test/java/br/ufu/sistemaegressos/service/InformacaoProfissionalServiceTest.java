package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.InformacaoProfissionalDTO;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.model.InformacaoProfissionalModel;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import br.ufu.sistemaegressos.repository.InformacaoProfissionalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InformacaoProfissionalServiceTest {

    @Mock
    private InformacaoProfissionalRepository informacaoProfissionalRepository;

    @Mock
    private InformacaoAcademicaRepository informacaoAcademicaRepository;

    @InjectMocks
    private InformacaoProfissionalService informacaoProfissionalService;

    private InformacaoProfissionalModel informacaoProfissional;
    private InformacaoAcademicaModel informacaoAcademica;
    private InformacaoProfissionalDTO informacaoProfissionalDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        informacaoAcademica = new InformacaoAcademicaModel();
        informacaoAcademica.setId(UUID.randomUUID());

        informacaoProfissional = new InformacaoProfissionalModel();
        informacaoProfissional.setId(UUID.randomUUID());
        informacaoProfissional.setCompany_name("Empresa Teste");
        informacaoProfissional.setInformacao_academica(informacaoAcademica);

        informacaoProfissionalDTO = new InformacaoProfissionalDTO();
        informacaoProfissionalDTO.setCompany_name("Empresa Teste");
        informacaoProfissionalDTO.setId(informacaoAcademica.getId());
    }

    @Test
    void testListarTodos() {
        when(informacaoProfissionalRepository.findAll()).thenReturn(Collections.singletonList(informacaoProfissional));
        List<InformacaoProfissionalModel> result = informacaoProfissionalService.listarTodos();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(informacaoProfissionalRepository, times(1)).findAll();
    }

    @Test
    void testListarPeloId() {
        when(informacaoProfissionalRepository.findById(informacaoProfissional.getId())).thenReturn(Optional.of(informacaoProfissional));
        Optional<InformacaoProfissionalModel> result = informacaoProfissionalService.listarPeloId(informacaoProfissional.getId());
        assertTrue(result.isPresent());
        assertEquals("Empresa Teste", result.get().getCompany_name());
        verify(informacaoProfissionalRepository, times(1)).findById(informacaoProfissional.getId());
    }

    @Test
    void testBuscarPorEgressoCpf() {
        when(informacaoProfissionalRepository.buscarPorEgresso("12345678900")).thenReturn(Collections.singletonList(informacaoProfissional));
        List<InformacaoProfissionalModel> result = informacaoProfissionalService.buscarPorEgressoCpf("12345678900");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(informacaoProfissionalRepository, times(1)).buscarPorEgresso("12345678900");
    }

    @Test
    void testCriarInformacaoProfissional() {
        when(informacaoAcademicaRepository.findById(informacaoProfissionalDTO.getId())).thenReturn(Optional.of(informacaoAcademica));
        when(informacaoProfissionalRepository.save(any(InformacaoProfissionalModel.class))).thenReturn(informacaoProfissional);
        InformacaoProfissionalModel result = informacaoProfissionalService.criarInformacaoProfissional(informacaoProfissionalDTO);
        assertNotNull(result);
        assertEquals("Empresa Teste", result.getCompany_name());
        verify(informacaoProfissionalRepository, times(1)).save(any(InformacaoProfissionalModel.class));
    }

    @Test
    void testAtualizarInformacaoProfissional() {
        when(informacaoAcademicaRepository.findById(informacaoProfissionalDTO.getId()))
                .thenReturn(Optional.of(informacaoAcademica));

        when(informacaoProfissionalRepository.findById(informacaoProfissional.getId()))
                .thenReturn(Optional.of(informacaoProfissional));

        when(informacaoProfissionalRepository.save(any(InformacaoProfissionalModel.class)))
                .thenReturn(informacaoProfissional);

        InformacaoProfissionalModel result = informacaoProfissionalService.atualizarInformacaoProfissional(informacaoProfissional.getId(), informacaoProfissionalDTO);

        assertNotNull(result);
        assertEquals("Empresa Teste", result.getCompany_name());
        verify(informacaoProfissionalRepository, times(1)).save(any(InformacaoProfissionalModel.class));
    }

    @Test
    void testExcluirInformacaoProfissional() {
        doNothing().when(informacaoProfissionalRepository).deleteById(informacaoProfissional.getId());
        informacaoProfissionalService.excluirInformacaoProfissional(informacaoProfissional.getId());
        verify(informacaoProfissionalRepository, times(1)).deleteById(informacaoProfissional.getId());
    }
}