package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.repository.DepoimentoRepository;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepoimentoServiceTest {

    @Mock
    private DepoimentoRepository depoimentoRepository;

    @Mock
    private InformacaoAcademicaRepository informacaoAcademicaRepository;

    @InjectMocks
    private DepoimentoService depoimentoService;

    private DepoimentoModel depoimento;
    private DepoimentoDTO depoimentoDTO;
    private InformacaoAcademicaModel informacaoAcademica;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        informacaoAcademica = new InformacaoAcademicaModel();
        informacaoAcademica.setId(UUID.randomUUID());
        informacaoAcademica.setCampus("Santa Monica");
        informacaoAcademica.setCourse_name("Economia");
        informacaoAcademica.setEnd_year(2023);
        informacaoAcademica.setEnd_semester("1° Semestre");
        informacaoAcademica.setCourse_level("Doutorado");

        depoimento = new DepoimentoModel();
        depoimento.setId(UUID.randomUUID());
        depoimento.setTexto_depoimento("Depoimento de teste");
        depoimento.setData_cadastro(LocalDate.now());
        depoimento.setInformacaoAcademica(informacaoAcademica);
        depoimento.setPrivacidade("Público");

        depoimentoDTO = new DepoimentoDTO();
        depoimentoDTO.setTexto_depoimento("Depoimento de teste");
        depoimentoDTO.setId_informacao_academica(informacaoAcademica.getId());
    }

    @Test
    public void testListarTodos() {
        when(depoimentoRepository.findAll()).thenReturn(Arrays.asList(depoimento));
        List<DepoimentoModel> result = depoimentoService.listarTodos("Santa Monica", "2023/1", "Economia", "Doutorado");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(depoimentoRepository, times(1)).findAll();
    }

    @Test
    public void testListarPeloId() {
        when(depoimentoRepository.findById(depoimento.getId())).thenReturn(Optional.of(depoimento));
        Optional<DepoimentoModel> result = depoimentoService.listarPeloId(depoimento.getId());
        assertTrue(result.isPresent());
        assertEquals(depoimento.getId(), result.get().getId());
        verify(depoimentoRepository, times(1)).findById(depoimento.getId());
    }

    @Test
    public void testCriarDepoimento() {
        when(informacaoAcademicaRepository.findById(depoimentoDTO.getId_informacao_academica())).thenReturn(Optional.of(informacaoAcademica));
        when(depoimentoRepository.save(any(DepoimentoModel.class))).thenReturn(depoimento);
        DepoimentoModel result = depoimentoService.criarDepoimento(depoimentoDTO);
        assertNotNull(result);
        assertEquals("Depoimento de teste", result.getTexto_depoimento());
        verify(depoimentoRepository, times(1)).save(any(DepoimentoModel.class));
    }

    @Test
    public void testExcluir() {
        doNothing().when(depoimentoRepository).deleteById(depoimento.getId());
        depoimentoService.excluir(depoimento.getId());
        verify(depoimentoRepository, times(1)).deleteById(depoimento.getId());
    }

    @Test
    public void testListarPorCpfEgresso() {
        when(depoimentoRepository.buscarPorEgresso("123456789")).thenReturn(Arrays.asList(depoimento));
        List<DepoimentoModel> result = depoimentoService.listarPorCpfEgresso("123456789");
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(depoimentoRepository, times(1)).buscarPorEgresso("123456789");
    }

    @Test
    public void testAtualizar() {
        when(depoimentoRepository.findById(depoimento.getId())).thenReturn(Optional.of(depoimento));
        when(informacaoAcademicaRepository.findById(depoimentoDTO.getId_informacao_academica()))
                .thenReturn(Optional.of(informacaoAcademica));
        when(depoimentoRepository.save(any(DepoimentoModel.class))).thenReturn(depoimento);

        DepoimentoModel result = depoimentoService.atualizar(depoimento.getId(), depoimentoDTO);

        assertNotNull(result);
        assertEquals("Depoimento de teste", result.getTexto_depoimento());
        verify(depoimentoRepository, times(1)).save(any(DepoimentoModel.class));
    }
}