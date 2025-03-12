package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.repository.DepoimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DepoimentoServiceTest {

    @Mock
    private DepoimentoRepository depoimentoRepository;

    @InjectMocks
    private DepoimentoService depoimentoService;

    private DepoimentoModel depoimento;
    private DepoimentoDTO depoimentoDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        depoimento = new DepoimentoModel();
        depoimento.setId("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7");
        depoimento.setTexto_depoimento("Depoimento de teste");
        depoimento.setData_cadastro(new Timestamp(System.currentTimeMillis()));

        depoimentoDTO = new DepoimentoDTO();
        depoimentoDTO.setTexto_depoimento("Depoimento de teste");
    }

    @Test
    public void testListarTodos() {
        when(depoimentoRepository.findAll()).thenReturn(Arrays.asList(depoimento));

        List<DepoimentoModel> result = depoimentoService.listarTodos("Santa Monica", 1000, "Economia", "Doutorado");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7", result.get(0).getId());
        verify(depoimentoRepository, times(1)).findAll();
    }

    @Test
    public void testListarPeloId() {
        when(depoimentoRepository.findById("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7")).thenReturn(Optional.of(depoimento));

        Optional<DepoimentoModel> result = depoimentoService.listarPeloId("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7");

        assertTrue(result.isPresent());
        assertEquals("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7", result.get().getId());
        verify(depoimentoRepository, times(1)).findById("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7");
    }

    @Test
    public void testCriar() {
        when(depoimentoRepository.save(any(DepoimentoModel.class))).thenReturn(depoimento);

        DepoimentoModel result = depoimentoService.criarDepoimento(depoimentoDTO);

        assertNotNull(result);
        assertEquals("Depoimento de teste", result.getTexto_depoimento());
        verify(depoimentoRepository, times(1)).save(any(DepoimentoModel.class));
    }

    @Test
    public void testExcluir() {
        doNothing().when(depoimentoRepository).deleteById("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7");

        depoimentoService.excluir("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7");

        verify(depoimentoRepository, times(1)).deleteById("f682e29a-dbb0-4c11-ac31-d9a89c72ebe7");
    }
}