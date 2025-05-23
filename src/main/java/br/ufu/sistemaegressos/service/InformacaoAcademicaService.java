package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.CursoNameDTO;
import br.ufu.sistemaegressos.dto.InformacaoAcademicaDTO;
import br.ufu.sistemaegressos.exceptions.ResourceNotFoundException;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import br.ufu.sistemaegressos.repository.EgressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InformacaoAcademicaService {

    private final InformacaoAcademicaRepository informacaoAcademicaRepository;
    private final EgressoRepository egressoRepository;

    @Autowired
    public InformacaoAcademicaService(
            InformacaoAcademicaRepository informacaoAcademicaRepository,
            EgressoRepository egressoRepository) {
        this.informacaoAcademicaRepository = informacaoAcademicaRepository;
        this.egressoRepository = egressoRepository;
    }

    public List<InformacaoAcademicaModel> buscarPorEgresso(String cpf) {
        List<InformacaoAcademicaModel> informacaoAcademica = informacaoAcademicaRepository.buscarPorEgresso(cpf);

        if (informacaoAcademica.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma informação acadêmica encontrada para o egresso com CPF: " + cpf);
        }

        return informacaoAcademica.stream().peek(informacao -> informacao.setComunicados(null)).collect(Collectors.toList());
    }

    public List<CursoNameDTO> buscarCursosPorCpf(String cpf) {
        return informacaoAcademicaRepository.buscarCursoPorCPF(cpf);
    }


    public List<InformacaoAcademicaModel> buscarPorInformacaoAcademica(UUID id) {
        List<InformacaoAcademicaModel> informacaoAcademica = informacaoAcademicaRepository.buscarPorInformacaoAcademica(id);

        if (informacaoAcademica.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma informação acadêmica encontrada com o ID: " + id);
        }

        return informacaoAcademica.stream().peek(informacao -> informacao.setComunicados(null)).collect(Collectors.toList());
    }

    public InformacaoAcademicaModel criar(InformacaoAcademicaDTO dto) {
        EgressoModel egresso = egressoRepository.findById(dto.getEgresso_cpf())
                .orElseThrow(() -> new ResourceNotFoundException("Egresso não encontrado com CPF: " + dto.getEgresso_cpf()));

        InformacaoAcademicaModel informacaoAcademica = new InformacaoAcademicaModel();
        BeanUtils.copyProperties(dto, informacaoAcademica);
        informacaoAcademica.setEgresso(egresso);
        return informacaoAcademicaRepository.save(informacaoAcademica);
    }

    public InformacaoAcademicaModel atualizar(UUID id, InformacaoAcademicaDTO dto) {
        InformacaoAcademicaModel informacaoAcademica = informacaoAcademicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Informação acadêmica não encontrada com ID: " + id));

        Optional.ofNullable(dto.getInstitution_name()).ifPresent(informacaoAcademica::setInstitution_name);
        Optional.ofNullable(dto.getCampus()).ifPresent(informacaoAcademica::setCampus);
        Optional.ofNullable(dto.getCourse_name()).ifPresent(informacaoAcademica::setCourse_name);
        Optional.ofNullable(dto.getCourse_level()).ifPresent(informacaoAcademica::setCourse_level);
        Optional.ofNullable(dto.getMatricula()).ifPresent(informacaoAcademica::setMatricula);
        Optional.ofNullable(dto.getStart_date()).ifPresent(informacaoAcademica::setStart_date);
        Optional.ofNullable(dto.getEnd_date()).ifPresent(informacaoAcademica::setEnd_date);
        Optional.ofNullable(dto.getInstitution_type()).ifPresent(informacaoAcademica::setInstitution_type);
        Optional.ofNullable(dto.getCity()).ifPresent(informacaoAcademica::setCity);
        Optional.ofNullable(dto.getState()).ifPresent(informacaoAcademica::setState);
        Optional.ofNullable(dto.getCountry()).ifPresent(informacaoAcademica::setCountry);
        Optional.ofNullable(dto.getRegistration_number()).ifPresent(informacaoAcademica::setRegistration_number);
        Optional.ofNullable(dto.getAtivo()).ifPresent(informacaoAcademica::setAtivo);

        informacaoAcademica = informacaoAcademicaRepository.save(informacaoAcademica);
        informacaoAcademica.setComunicados(null);
        return informacaoAcademica;
    }

    public void excluir(UUID id) {
        if (!informacaoAcademicaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Informação acadêmica não encontrada com ID: " + id);
        }
        informacaoAcademicaRepository.deleteById(id);
    }

    public List<InformacaoAcademicaModel> buscarInformacoesAcademicasSemDepoimento(String cpf) {
        List<InformacaoAcademicaModel> informacoesAcademicas = informacaoAcademicaRepository.buscarInformacoesAcademicasSemDepoimento(cpf);

        if (informacoesAcademicas.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma informação acadêmica sem depoimento encontrada para o CPF: " + cpf);
        }

        return informacoesAcademicas.stream().peek(informacao -> {
            informacao.setComunicados(null);
            informacao.setEgresso(null);
        }).collect(Collectors.toList());
    }
}
