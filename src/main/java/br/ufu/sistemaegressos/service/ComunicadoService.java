package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.ComunicadoDTO;
import br.ufu.sistemaegressos.exceptions.ResourceNotFoundException;
import br.ufu.sistemaegressos.model.*;
import br.ufu.sistemaegressos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class ComunicadoService {

    private final ComunicadoRepository comunicadoRepository;
    private final InformacaoAcademicaRepository informacaoAcademicaRepository;

    @Autowired
    public ComunicadoService(
            ComunicadoRepository comunicadoRepository,
            InformacaoAcademicaRepository informacaoAcademicaRepository) {
        this.comunicadoRepository = comunicadoRepository;
        this.informacaoAcademicaRepository = informacaoAcademicaRepository;
    }

    public List<ComunicadoModel> listarTodos() {
        List<ComunicadoModel> comunicados = comunicadoRepository.findAll();

        if (comunicados.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum comunicado encontrado.");
        }

        for (ComunicadoModel comunicado : comunicados) {
            if (comunicado.getInformacao_academica() != null) {
                comunicado.setInformacao_academica(null);
            }
        }
        return comunicados;
    }

    public List<ComunicadoModel> buscarPorFiltro(String cursoDestino, String nivelCursoDestino, Boolean paraTodos) {
        List<ComunicadoModel> comunicados = comunicadoRepository.buscarComunicadosFiltrados(cursoDestino, nivelCursoDestino, paraTodos);

        if (comunicados.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum comunicado encontrado com os filtros informados.");
        }

        for (ComunicadoModel comunicado : comunicados) {
            if (comunicado.getInformacao_academica() != null) {
                comunicado.setInformacao_academica(null);
            }
        }

        return comunicados;
    }

    public ComunicadoModel criarComunicado(ComunicadoDTO comunicadoDTO) {
        ComunicadoModel comunicado = new ComunicadoModel();
        BeanUtils.copyProperties(comunicadoDTO, comunicado);
        comunicado.setData_envio(LocalDate.now());

        comunicado = comunicadoRepository.save(comunicado);

        boolean temCurso = comunicado.getCurso_destino() != null && !comunicado.getCurso_destino().isBlank();
        boolean temNivel = comunicado.getNivel_curso_destino() != null && !comunicado.getNivel_curso_destino().isBlank();

        if (temCurso || temNivel) {
            List<InformacaoAcademicaModel> informacoesAcademicas;

            if (temCurso && temNivel) {
                informacoesAcademicas = informacaoAcademicaRepository.buscarPorNomeENivelCurso(
                        comunicado.getCurso_destino(), comunicado.getNivel_curso_destino()
                );
            } else if (temCurso) {
                informacoesAcademicas = informacaoAcademicaRepository.buscarPorNomeCurso(comunicado.getCurso_destino());
            } else {
                informacoesAcademicas = informacaoAcademicaRepository.buscarPorNivelCurso(comunicado.getNivel_curso_destino());
            }

            if (informacoesAcademicas.isEmpty()) {
                throw new ResourceNotFoundException("Nenhuma informação acadêmica encontrada para o filtro aplicado ao comunicado.");
            }

            for (InformacaoAcademicaModel info : informacoesAcademicas) {
                info.setComunicados(null);
                comunicado.getInformacao_academica().add(info);
            }

            comunicado = comunicadoRepository.save(comunicado);
        }
        return comunicado;
    }
}
