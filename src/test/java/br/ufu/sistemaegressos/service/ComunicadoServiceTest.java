package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.ComunicadoDTO;
import br.ufu.sistemaegressos.model.ComunicadoModel;
import br.ufu.sistemaegressos.repository.ComunicadoRepository;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ComunicadoServiceTest {

    @Mock
    private ComunicadoRepository comunicadoRepository;

    @Mock
    private InformacaoAcademicaRepository informacaoAcademicaRepository;

    @InjectMocks
    private ComunicadoService comunicadoService;

    private ComunicadoModel comunicado;
    private ComunicadoDTO comunicadoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        comunicado = new ComunicadoModel();
        comunicado.setId(UUID.randomUUID());
        comunicado.setTitulo("Test Title");
        comunicado.setTexto_comunicado("Test Text");
        comunicado.setData_envio(LocalDate.now());

        comunicadoDTO = new ComunicadoDTO();
        comunicadoDTO.setTitulo("Test Title");
        comunicadoDTO.setTexto_comunicado("Test Text");
    }

    @Test
    void testListarTodos() {
        List<ComunicadoModel> comunicados = new ArrayList<>();
        comunicados.add(comunicado);
        when(comunicadoRepository.findAll()).thenReturn(comunicados);

        List<ComunicadoModel> result = comunicadoService.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertNull(result.get(0).getInformacao_academica());
        verify(comunicadoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorFiltro() {
        List<ComunicadoModel> comunicados = new ArrayList<>();
        comunicados.add(comunicado);
        when(comunicadoRepository.buscarComunicadosFiltrados(any(), any(), any())).thenReturn(comunicados);

        List<ComunicadoModel> result = comunicadoService.buscarPorFiltro("Curso", "Nivel", true);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertNull(result.get(0).getInformacao_academica());
        verify(comunicadoRepository, times(1)).buscarComunicadosFiltrados(any(), any(), any());
    }

    @Test
    void testCriarComunicado() {
        when(comunicadoRepository.save(any(ComunicadoModel.class))).thenReturn(comunicado);
        when(informacaoAcademicaRepository.buscarPorNomeENivelCurso(any(), any())).thenReturn(new ArrayList<>());

        ComunicadoModel result = comunicadoService.criarComunicado(comunicadoDTO);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitulo());
        assertEquals("Test Text", result.getTexto_comunicado());
        verify(comunicadoRepository, times(1)).save(any(ComunicadoModel.class));
    }
}