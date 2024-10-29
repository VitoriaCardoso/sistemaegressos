package br.ufu.sistemaegressos.service;

import static org.junit.jupiter.api.Assertions.*;
import br.ufu.sistemaegressos.dto.EgressoAtualizarDTO;
import br.ufu.sistemaegressos.dto.EgressoCriarDTO;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.repository.EgressoRepository;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class EgressoServiceTest {
    @Mock
    private EgressoRepository egressoRepository;

    @InjectMocks
    private EgressoService egressoService;

    private EgressoModel egresso;
    private EgressoCriarDTO criarDTO;
    private EgressoAtualizarDTO atualizarDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        egresso = new EgressoModel();
        egresso.setCpf("12345678900");
        egresso.setNome("João Silva");
        egresso.setDataAtualizacao(new Timestamp(System.currentTimeMillis()));

        criarDTO = new EgressoCriarDTO();
        criarDTO.setCpf("12345678900");
        criarDTO.setNome("João Silva");

        atualizarDTO = new EgressoAtualizarDTO();
        atualizarDTO.setNomeSocial("João S.");
        atualizarDTO.setTelefone("999999999");
    }

    @Test
    public void testBuscarTodos() {
        when(egressoRepository.findAll()).thenReturn(Arrays.asList(egresso));

        List<EgressoModel> result = egressoService.buscarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("12345678900", result.get(0).getCpf());
        verify(egressoRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPeloCPF() {
        when(egressoRepository.findById("12345678900")).thenReturn(Optional.of(egresso));

        Optional<EgressoModel> result = egressoService.buscarPeloCPF("12345678900");

        assertTrue(result.isPresent());
        assertEquals("12345678900", result.get().getCpf());
        verify(egressoRepository, times(1)).findById("12345678900");
    }

    @Test
    public void testCriar() {
        when(egressoRepository.save(any(EgressoModel.class))).thenReturn(egresso);

        EgressoModel result = egressoService.criar(criarDTO);

        assertNotNull(result);
        assertEquals("12345678900", result.getCpf());
        assertEquals("João Silva", result.getNome());
        verify(egressoRepository, times(1)).save(any(EgressoModel.class));
    }

    @Test
    public void testAtualizar() {
        when(egressoRepository.findById("12345678900")).thenReturn(Optional.of(egresso));
        when(egressoRepository.save(any(EgressoModel.class))).thenReturn(egresso);

        EgressoModel result = egressoService.atualizar("12345678900", atualizarDTO);

        assertNotNull(result);
        assertEquals("João S.", result.getNomeSocial());
        assertEquals("999999999", result.getTelefone());
        verify(egressoRepository, times(1)).findById("12345678900");
        verify(egressoRepository, times(1)).save(any(EgressoModel.class));
    }

    @Test
    public void testAtualizarNotFound() {
        when(egressoRepository.findById("12345678900")).thenReturn(Optional.empty());

        EgressoModel result = egressoService.atualizar("12345678900", atualizarDTO);

        assertNull(result);
        verify(egressoRepository, times(1)).findById("12345678900");
        verify(egressoRepository, never()).save(any(EgressoModel.class));
    }

    @Test
    public void testExcluir() {
        doNothing().when(egressoRepository).deleteById("12345678900");

        egressoService.excluir("12345678900");

        verify(egressoRepository, times(1)).deleteById("12345678900");
    }
}