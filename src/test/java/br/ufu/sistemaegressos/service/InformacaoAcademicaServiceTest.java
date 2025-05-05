package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.InformacaoAcademicaDTO;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.repository.EgressoRepository;
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

class InformacaoAcademicaServiceTest {

    @Mock
    private InformacaoAcademicaRepository informacaoAcademicaRepository;

    @Mock
    private EgressoRepository egressoRepository;

    @InjectMocks
    private InformacaoAcademicaService informacaoAcademicaService;

    private InformacaoAcademicaModel informacaoAcademica;
    private EgressoModel egresso;
    private InformacaoAcademicaDTO informacaoAcademicaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        egresso = new EgressoModel();
        egresso.setCpf("12345678910");

        informacaoAcademica = new InformacaoAcademicaModel();
        informacaoAcademica.setId(UUID.randomUUID());
        informacaoAcademica.setEgresso(egresso);

        informacaoAcademicaDTO = new InformacaoAcademicaDTO();
        informacaoAcademicaDTO.setEgresso_cpf("12345678910");
    }

    @Test
    void testBuscarPorEgresso() {
        when(informacaoAcademicaRepository.buscarPorEgresso("12345678910"))
                .thenReturn(Arrays.asList(informacaoAcademica));

        List<InformacaoAcademicaModel> result = informacaoAcademicaService.buscarPorEgresso("12345678910");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(informacaoAcademicaRepository, times(1)).buscarPorEgresso("12345678910");
    }

    @Test
    void testBuscarPorInformacaoAcademica() {
        when(informacaoAcademicaRepository.buscarPorInformacaoAcademica(informacaoAcademica.getId()))
                .thenReturn(Arrays.asList(informacaoAcademica));

        List<InformacaoAcademicaModel> result = informacaoAcademicaService.buscarPorInformacaoAcademica(informacaoAcademica.getId());

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(informacaoAcademicaRepository, times(1)).buscarPorInformacaoAcademica(informacaoAcademica.getId());
    }

    @Test
    void testCriar() {
        when(egressoRepository.findById("12345678910")).thenReturn(Optional.of(egresso));
        when(informacaoAcademicaRepository.save(any(InformacaoAcademicaModel.class)))
                .thenReturn(informacaoAcademica);

        InformacaoAcademicaModel result = informacaoAcademicaService.criar(informacaoAcademicaDTO);

        assertNotNull(result);
        assertEquals(egresso, result.getEgresso());
        verify(informacaoAcademicaRepository, times(1)).save(any(InformacaoAcademicaModel.class));
    }

    @Test
    void testAtualizar() {
        when(informacaoAcademicaRepository.findById(informacaoAcademica.getId()))
                .thenReturn(Optional.of(informacaoAcademica));
        when(informacaoAcademicaRepository.save(any(InformacaoAcademicaModel.class)))
                .thenReturn(informacaoAcademica);

        InformacaoAcademicaModel result = informacaoAcademicaService.atualizar(informacaoAcademica.getId(), informacaoAcademicaDTO);

        assertNotNull(result);
        verify(informacaoAcademicaRepository, times(1)).save(any(InformacaoAcademicaModel.class));
    }

    @Test
    void testExcluir() {
        doNothing().when(informacaoAcademicaRepository).deleteById(informacaoAcademica.getId());

        informacaoAcademicaService.excluir(informacaoAcademica.getId());

        verify(informacaoAcademicaRepository, times(1)).deleteById(informacaoAcademica.getId());
    }
}