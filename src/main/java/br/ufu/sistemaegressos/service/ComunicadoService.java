package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.ComunicadoDTO;
import br.ufu.sistemaegressos.model.*;
import br.ufu.sistemaegressos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
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
        for (ComunicadoModel comunicado : comunicados) {
            if (comunicado.getInformacao_academica() != null) {
                for (InformacaoAcademicaModel informacaoAcademica : comunicado.getInformacao_academica()) {
                    informacaoAcademica.setInformacao_profissional(null);
                    informacaoAcademica.setComunicados(null);
                }
            }
        }
        return comunicados;
    }

    public List<ComunicadoModel> buscarPorFiltro(String cursoDestino, String nivelCursoDestino, Boolean paraTodos) {
        List<ComunicadoModel> comunicados;

        if (cursoDestino != null && nivelCursoDestino != null) {
            comunicados = comunicadoRepository.buscarComunicadoPorCursoEnivel(cursoDestino, nivelCursoDestino);
        } else if (cursoDestino != null) {
            comunicados = comunicadoRepository.buscarComunicadoPorCurso(cursoDestino);
        } else if (nivelCursoDestino != null) {
            comunicados = comunicadoRepository.buscarComunicadosPorNivelCurso(nivelCursoDestino);
        } else if (paraTodos != null) {
            comunicados = comunicadoRepository.buscarComunicadosParaTodos(paraTodos);
        } else {
            comunicados = comunicadoRepository.findAll();
        }

        for (ComunicadoModel comunicado : comunicados) {
            if (comunicado.getInformacao_academica() != null) {
                for (InformacaoAcademicaModel informacaoAcademica : comunicado.getInformacao_academica()) {
                    informacaoAcademica.setInformacao_profissional(null);
                    informacaoAcademica.setComunicados(null);
                }
            }
        }

        return comunicados;
    }

    public ComunicadoModel criarComunicado(ComunicadoDTO comunicadoDTO) {
        ComunicadoModel comunicado = new ComunicadoModel();
        BeanUtils.copyProperties(comunicadoDTO, comunicado);
        comunicado.setData_envio(new Timestamp(System.currentTimeMillis()));

        comunicado = comunicadoRepository.save(comunicado);

        if (!comunicado.getPara_todos()) {
            List<InformacaoAcademicaModel> informacoesAcademicas = informacaoAcademicaRepository.buscarPorNomeCurso(comunicado.getCurso_destino());
            for (InformacaoAcademicaModel informacaoAcademica : informacoesAcademicas) {
                informacaoAcademica.setInformacao_profissional(null);
                informacaoAcademica.setComunicados(null);
                comunicado.getInformacao_academica().add(informacaoAcademica);
            }
            comunicadoRepository.save(comunicado);
        }
        return comunicado;
    }
}
