package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.InformacaoProfissionalDTO;
import br.ufu.sistemaegressos.model.*;
import br.ufu.sistemaegressos.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InformacaoProfissionalService {

    private final InformacaoProfissionalRepository informacaoProfissionalRepository;
    private final InformacaoAcademicaRepository informacaoAcademicaRepository;

    @Autowired
    public InformacaoProfissionalService(
            InformacaoProfissionalRepository informacaoProfissionalRepository,
            InformacaoAcademicaRepository informacaoAcademicaRepository) {
        this.informacaoProfissionalRepository = informacaoProfissionalRepository;
        this.informacaoAcademicaRepository = informacaoAcademicaRepository;
    }

    public List<InformacaoProfissionalModel> listarTodos() {
        List<InformacaoProfissionalModel> informacoesProfissionais = informacaoProfissionalRepository.findAll();
        for (InformacaoProfissionalModel informacaoProfissional : informacoesProfissionais) {
            Set<InformacaoAcademicaModel> informacoesAcademicas = informacaoProfissional.getInformacao_academica();
            for (InformacaoAcademicaModel informacaoAcademica : informacoesAcademicas) {
                informacaoAcademica.setInformacao_profissional(null);
                informacaoAcademica.setComunicados(null);
            }
        }
        return informacoesProfissionais;
    }

    public Optional<InformacaoProfissionalModel> listarPeloId(UUID id) {
        Optional<InformacaoProfissionalModel> informacaoProfissional = informacaoProfissionalRepository.findById(id);
        if (informacaoProfissional.isPresent()) {
            Set<InformacaoAcademicaModel> informacoesAcademicas = informacaoProfissional.get().getInformacao_academica();
            for (InformacaoAcademicaModel informacaoAcademica : informacoesAcademicas) {
                informacaoAcademica.setInformacao_profissional(null);
                informacaoAcademica.setComunicados(null);
            }
        }
        return informacaoProfissional;
    }

    public List<InformacaoProfissionalModel> buscarPorEgressoCpf(String cpf) {
        List<InformacaoProfissionalModel> informacaoProfissional = informacaoProfissionalRepository.buscarPorEgresso(cpf);

        return informacaoProfissional.stream().map(informacao -> {
            Set<InformacaoAcademicaModel> informacoesAcademicas = informacao.getInformacao_academica();
            informacoesAcademicas.stream().map(informacaoAcademica -> {
                informacaoAcademica.setInformacao_profissional(null);
                informacaoAcademica.setComunicados(null);
                return informacaoAcademica;
            }).collect(Collectors.toList());
            return informacao;
        }).collect(Collectors.toList());
    }

    public InformacaoProfissionalModel criarInformacaoProfissional(InformacaoProfissionalDTO dto) {
        InformacaoProfissionalModel informacaoProfissional = new InformacaoProfissionalModel();
        BeanUtils.copyProperties(dto, informacaoProfissional);
        informacaoProfissional.setData_inicio(dto.getData_inicio());

        InformacaoAcademicaModel informacaoAcademica = informacaoAcademicaRepository
                .findById(dto.getId_informacao_academica())
                .orElseThrow(() -> new RuntimeException("Informação acadêmica não encontrada para o ID: " + dto.getId_informacao_academica()));

        informacaoProfissional.getInformacao_academica().add(informacaoAcademica);

        informacaoProfissional = informacaoProfissionalRepository.save(informacaoProfissional);

        Set<InformacaoAcademicaModel> informacoesAcademicas = informacaoProfissional.getInformacao_academica();
        informacoesAcademicas.stream().map(informacao -> {
            informacao.setInformacao_profissional(null);
            informacao.setComunicados(null);
            return informacao;
        }).collect(Collectors.toList());

        return informacaoProfissional;
    }

    public InformacaoProfissionalModel atualizarInformacaoProfissional(UUID id, InformacaoProfissionalDTO dto) {
        InformacaoProfissionalModel informacaoProfissional = informacaoProfissionalRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Informação profissional não encontrada para o ID: " + id));

        Optional.ofNullable(dto.getEmpresa()).ifPresent(informacaoProfissional::setEmpresa);
        Optional.ofNullable(dto.getCategoria()).ifPresent(informacaoProfissional::setCategoria);
        Optional.ofNullable(dto.getTipo()).ifPresent(informacaoProfissional::setTipo);
        Optional.ofNullable(dto.getLocalidade()).ifPresent(informacaoProfissional::setLocalidade);
        Optional.ofNullable(dto.getCargo()).ifPresent(informacaoProfissional::setCargo);
        Optional.ofNullable(dto.getNivel_cargo()).ifPresent(informacaoProfissional::setNivel_cargo);
        Optional.ofNullable(dto.getFuncao()).ifPresent(informacaoProfissional::setFuncao);
        Optional.ofNullable(dto.getMedia_salarial()).ifPresent(informacaoProfissional::setMedia_salarial);
        Optional.ofNullable(dto.getData_inicio()).ifPresent(informacaoProfissional::setData_inicio);
        Optional.ofNullable(dto.getData_fim()).ifPresent(informacaoProfissional::setData_fim);

        Optional.ofNullable(dto.getId_informacao_academica()).ifPresent(idInfoAcademica -> {
            InformacaoAcademicaModel informacaoAcademica = informacaoAcademicaRepository
                    .findById(idInfoAcademica)
                    .orElseThrow(() -> new RuntimeException("Informação acadêmica não encontrada para o ID: " + idInfoAcademica));

            informacaoProfissional.getInformacao_academica().clear();
            informacaoProfissional.getInformacao_academica().add(informacaoAcademica);
        });

        return informacaoProfissionalRepository.save(informacaoProfissional);
    }


    public void excluirInformacaoProfissional(UUID id) {
        informacaoProfissionalRepository.deleteById(id);
    }
}
