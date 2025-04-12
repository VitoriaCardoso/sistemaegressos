package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.repository.DepoimentoRepository;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepoimentoService {

    private final DepoimentoRepository depoimentoRepository;
    private final InformacaoAcademicaRepository informacaoAcademicaRepository;

    @Autowired
    public DepoimentoService(DepoimentoRepository depoimentoRepository, InformacaoAcademicaRepository informacaoAcademicaRepository) {
        this.depoimentoRepository = depoimentoRepository;
        this.informacaoAcademicaRepository = informacaoAcademicaRepository;
    }

    public List<DepoimentoModel> listarTodos(String campus, Integer totalEstudantes, String curso, String titulacao) {
        List<DepoimentoModel> depoimentos = depoimentoRepository.findAll();
        List<DepoimentoModel> resultado = new ArrayList<>();

        // Buscar total de estudantes agrupados por curso e campus
        List<Object[]> totalEstudantesPorCursoECampus = informacaoAcademicaRepository.contarEstudantesPorCursoECampus();
        Map<String, Map<String, Long>> totalEstudantesMap = new HashMap<>();

        for (Object[] row : totalEstudantesPorCursoECampus) {
            String nomeCurso = (String) row[0];
            String nomeCampus = (String) row[1];
            Long total = (Long) row[2];

            totalEstudantesMap
                    .computeIfAbsent(nomeCampus, k -> new HashMap<>())
                    .put(nomeCurso, total);
        }

        for (DepoimentoModel depoimento : depoimentos) {
            boolean adicionar = true;
            InformacaoAcademicaModel infoAcademica = depoimento.getInformacaoAcademica();

            if (infoAcademica == null) {
                continue;
            }

            if (campus != null && (infoAcademica.getInstitution_name() == null || !infoAcademica.getInstitution_name().equalsIgnoreCase(campus))) {
                adicionar = false;
            }

            if (curso != null && (infoAcademica.getNome_curso() == null || !infoAcademica.getNome_curso().equalsIgnoreCase(curso))) {
                adicionar = false;
            }

            if (totalEstudantes != null) {
                Long estudantes = totalEstudantesMap
                        .getOrDefault(infoAcademica.getInstitution_name(), new HashMap<>())
                        .getOrDefault(infoAcademica.getNome_curso(), 0L);

                if (estudantes.intValue() != totalEstudantes) {
                    adicionar = false;
                }
            }

            if (titulacao != null && (infoAcademica.getCourse_level() == null || !infoAcademica.getCourse_level().equalsIgnoreCase(titulacao))) {
                adicionar = false;
            }

            if (adicionar) {
                infoAcademica.setInformacao_profissional(null);
                infoAcademica.setComunicados(null);
                resultado.add(depoimento);
            }
        }
        return resultado;
    }


    public Optional<DepoimentoModel> listarPeloId(UUID id) {
        Optional<DepoimentoModel> depoimento = depoimentoRepository.findById(id);
        if (depoimento.isPresent()) {
            InformacaoAcademicaModel informacaoAcademica = depoimento.get().getInformacaoAcademica();
            if (informacaoAcademica != null) {
                informacaoAcademica.setInformacao_profissional(null);
                informacaoAcademica.setComunicados(null);
            }
        }
        return depoimento;
    }

    public DepoimentoModel criarDepoimento(DepoimentoDTO depoimentoDTO){
        DepoimentoModel depoimento = new DepoimentoModel();
        BeanUtils.copyProperties(depoimentoDTO, depoimento);
        depoimento.setData_cadastro(LocalDate.now());
        Optional<InformacaoAcademicaModel> informacaoAcademica = informacaoAcademicaRepository.findById(depoimentoDTO.getId_informacao_academica());
        if (informacaoAcademica.isPresent()) {
            depoimento.setInformacaoAcademica(informacaoAcademica.get());
            depoimento.getInformacaoAcademica().setComunicados(null);
            depoimento.getInformacaoAcademica().setInformacao_profissional(null);
        } else {
            throw new RuntimeException("Informação acadêmica não encontrada para a matrícula: " + depoimentoDTO.getId_informacao_academica());
        }
        return depoimentoRepository.save(depoimento);
    }

    public void excluir(UUID id){ depoimentoRepository.deleteById(id);}

    public List<DepoimentoModel> listarPorCpfEgresso(String cpf) {
        List<DepoimentoModel> depoimentos = depoimentoRepository.buscarPorEgresso(cpf);

        return depoimentos.stream().map(depoimento -> {
            depoimento.setInformacaoAcademica(null);
            return depoimento;
        }).collect(Collectors.toList());
    }
}
