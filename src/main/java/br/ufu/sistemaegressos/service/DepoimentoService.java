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

    public List<DepoimentoModel> listarTodos(String campus, String semestreLetivo, String curso, String titulacao) {
        List<DepoimentoModel> depoimentos = depoimentoRepository.findAll();
        List<DepoimentoModel> resultado = new ArrayList<>();

        // Buscar total de estudantes agrupados por curso e campus
        List<Object[]> totalEstudantesPorCursoECampus = informacaoAcademicaRepository.contarEstudantesPorCursoECampus();
        Map<String, Map<String, Long>> totalEstudantesMap = new HashMap<>();

        for (Object[] row : totalEstudantesPorCursoECampus) {
            String nomeCurso = (String) row[0];
            String nomeCampus = (String) row[1];
            Long total = (Long) row[3];

            totalEstudantesMap
                    .computeIfAbsent(nomeCampus, k -> new HashMap<>())
                    .put(nomeCurso, total);
        }

        for (DepoimentoModel depoimento : depoimentos) {
            if (!Arrays.asList("Público", "Anônimo").contains(depoimento.getPrivacidade())) {
                continue;
            }

            boolean adicionar = true;
            InformacaoAcademicaModel infoAcademica = depoimento.getInformacaoAcademica();

            if (infoAcademica == null) {
                continue;
            }

            if (campus != null && (infoAcademica.getCampus() == null || !infoAcademica.getCampus().equalsIgnoreCase(campus))) {
                adicionar = false;
            }

            if (curso != null && (infoAcademica.getCourse_name() == null || !infoAcademica.getCourse_name().equalsIgnoreCase(curso))) {
                adicionar = false;
            }

            if (semestreLetivo != null) {
                String[] partes = semestreLetivo.split("/");
                if (partes.length == 2) {
                    int ano = Integer.parseInt(partes[0]);
                    String semestre = partes[1].equals("1") ? "1° Semestre" :
                            partes[1].equals("2") ? "2° Semestre" : null;

                    Integer anoFinal = infoAcademica.getEnd_year();
                    String semestreFinal = infoAcademica.getEnd_semester();

                    boolean correspondeSemestre = anoFinal != null && semestreFinal != null &&
                            anoFinal == ano && semestreFinal.equalsIgnoreCase(semestre);

                    if (!correspondeSemestre) {
                        adicionar = false;
                    }
                } else {
                    adicionar = false;
                }
            }

            if (titulacao != null && (infoAcademica.getCourse_level() == null || !infoAcademica.getCourse_level().equalsIgnoreCase(titulacao))) {
                adicionar = false;
            }

            if (adicionar) {
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
        } else {
            throw new RuntimeException("Informação acadêmica não encontrada para o ID: " + depoimentoDTO.getId_informacao_academica());
        }
        return depoimentoRepository.save(depoimento);
    }

    public void excluir(UUID id){ depoimentoRepository.deleteById(id);}

    public List<DepoimentoModel> listarPorCpfEgresso(String cpf) {
        List<DepoimentoModel> depoimentos = depoimentoRepository.buscarPorEgresso(cpf);

        return depoimentos.stream().map(depoimento -> {
            InformacaoAcademicaModel informacaoAcademica = depoimento.getInformacaoAcademica();
            informacaoAcademica.setEgresso(null);
            informacaoAcademica.setComunicados(null);
            return depoimento;
        }).collect(Collectors.toList());
    }

    public DepoimentoModel atualizar(UUID id, DepoimentoDTO depoimentoDTO) {
        Optional<DepoimentoModel> depoimentoOptional = depoimentoRepository.findById(id);

        if (depoimentoOptional.isEmpty()) {
            throw new RuntimeException("Depoimento não encontrado com o id: " + id);
        }

        DepoimentoModel depoimentoExistente = depoimentoOptional.get();

        if (depoimentoDTO.getTexto_depoimento() != null) {
            depoimentoExistente.setTexto_depoimento(depoimentoDTO.getTexto_depoimento());
        }

        if (depoimentoDTO.getPrivacidade() != null) {
            depoimentoExistente.setPrivacidade(depoimentoDTO.getPrivacidade());
        }

        if (depoimentoDTO.getId_informacao_academica() != null) {
            Optional<InformacaoAcademicaModel> infoAcademicaOptional = informacaoAcademicaRepository.findById(depoimentoDTO.getId_informacao_academica());
            if (infoAcademicaOptional.isPresent()) {
                InformacaoAcademicaModel info = infoAcademicaOptional.get();
                info.setComunicados(null);
                depoimentoExistente.setInformacaoAcademica(info);
            } else {
                throw new RuntimeException("Informação acadêmica não encontrada para o ID: " + depoimentoDTO.getId_informacao_academica());
            }
        }
        return depoimentoRepository.save(depoimentoExistente);
    }
}
