package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.InformacaoProfissionalDTO;
import br.ufu.sistemaegressos.exceptions.ResourceNotFoundException;
import br.ufu.sistemaegressos.model.*;
import br.ufu.sistemaegressos.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        if (informacoesProfissionais.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma informação profissional encontrada.");
        }

        for (InformacaoProfissionalModel informacaoProfissional : informacoesProfissionais) {
            InformacaoAcademicaModel informacaoAcademica = informacaoProfissional.getInformacao_academica();
            informacaoAcademica.setComunicados(null);
        }
        return informacoesProfissionais;
    }

    public Optional<InformacaoProfissionalModel> listarPeloId(UUID id) {
        Optional<InformacaoProfissionalModel> informacaoProfissional = informacaoProfissionalRepository.findById(id);
        if (informacaoProfissional.isPresent()) {
            InformacaoAcademicaModel informacaoAcademica = informacaoProfissional.get().getInformacao_academica();
            informacaoAcademica.setComunicados(null);
            return informacaoProfissional;
        } else {
            throw new ResourceNotFoundException("Informação profissional não encontrada para o ID: " + id);
        }
    }

    public List<InformacaoProfissionalModel> buscarPorEgressoCpf(String cpf) {
        List<InformacaoProfissionalModel> informacaoProfissional = informacaoProfissionalRepository.buscarPorEgresso(cpf);

        if (informacaoProfissional.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma informação profissional encontrada para o CPF: " + cpf);
        }

        return informacaoProfissional.stream().peek(informacao -> {
            InformacaoAcademicaModel informacaoAcademica = informacao.getInformacao_academica();
            informacaoAcademica.setComunicados(null);
        }).collect(Collectors.toList());
    }

    public InformacaoProfissionalModel criarInformacaoProfissional(InformacaoProfissionalDTO dto) {
        InformacaoProfissionalModel informacaoProfissional = new InformacaoProfissionalModel();
        BeanUtils.copyProperties(dto, informacaoProfissional);
        informacaoProfissional.setStart_date(dto.getStart_date());

        InformacaoAcademicaModel informacaoAcademica = informacaoAcademicaRepository
                .findById(dto.getInformacao_academica())
                .orElseThrow(() -> new ResourceNotFoundException("Informação acadêmica não encontrada para o ID: " + dto.getInformacao_academica()));

        informacaoProfissional.setInformacao_academica(informacaoAcademica);

        informacaoProfissional = informacaoProfissionalRepository.save(informacaoProfissional);

        return informacaoProfissional;
    }

    public InformacaoProfissionalModel atualizarInformacaoProfissional(UUID id, InformacaoProfissionalDTO dto) {
        InformacaoProfissionalModel informacaoProfissional = informacaoProfissionalRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Informação profissional não encontrada para o ID: " + id));

        Optional.ofNullable(dto.getCompany_name()).ifPresent(informacaoProfissional::setCompany_name);
        Optional.ofNullable(dto.getCategory()).ifPresent(informacaoProfissional::setCategory);
        Optional.ofNullable(dto.getJob_type()).ifPresent(informacaoProfissional::setJob_type);
        Optional.ofNullable(dto.getLocation()).ifPresent(informacaoProfissional::setLocation);
        Optional.ofNullable(dto.getJob_title()).ifPresent(informacaoProfissional::setJob_title);
        Optional.ofNullable(dto.getJob_level()).ifPresent(informacaoProfissional::setJob_level);
        Optional.ofNullable(dto.getFunction()).ifPresent(informacaoProfissional::setFunction);
        Optional.ofNullable(dto.getSalary()).ifPresent(informacaoProfissional::setSalary);
        Optional.ofNullable(dto.getStart_date()).ifPresent(informacaoProfissional::setStart_date);
        Optional.ofNullable(dto.getEnd_date()).ifPresent(informacaoProfissional::setEnd_date);
        BeanUtils.copyProperties(dto, informacaoProfissional, "id");

        Optional.ofNullable(dto.getInformacao_academica()).ifPresent(idInfoAcademica -> {
            InformacaoAcademicaModel informacaoAcademica = informacaoAcademicaRepository
                    .findById(idInfoAcademica)
                    .orElseThrow(() -> new ResourceNotFoundException("Informação acadêmica não encontrada para o ID: " + idInfoAcademica));

            informacaoProfissional.setInformacao_academica(informacaoAcademica);
        });

        return informacaoProfissionalRepository.save(informacaoProfissional);
    }


    public void excluirInformacaoProfissional(UUID id) {
        if (!informacaoProfissionalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Informação profissional não encontrada para exclusão com o ID: " + id);
        }
        informacaoProfissionalRepository.deleteById(id);
    }
}
