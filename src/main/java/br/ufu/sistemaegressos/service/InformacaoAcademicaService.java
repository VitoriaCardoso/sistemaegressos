package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.InformacaoAcademicaDTO;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import br.ufu.sistemaegressos.repository.EgressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InformacaoAcademicaService {

    private final InformacaoAcademicaRepository informacaoAcademicaRepository;
    private final EgressoRepository egressoRepository;

    @Autowired
    public InformacaoAcademicaService(InformacaoAcademicaRepository informacaoAcademicaRepository, EgressoRepository egressoRepository) {
        this.informacaoAcademicaRepository = informacaoAcademicaRepository;
        this.egressoRepository = egressoRepository;
    }

    public List<InformacaoAcademicaModel> buscarPorEgresso(String cpf) {
        List<InformacaoAcademicaModel> informacaoAcademica = informacaoAcademicaRepository.buscarPorEgresso(cpf);

        return informacaoAcademica.stream().map(informacao -> {
            informacao.setInformacao_profissional(null);
            informacao.setComunicados(null);
            return informacao;
        }).collect(Collectors.toList());
    }

    public List<InformacaoAcademicaModel> buscarPorInformacaoAcademica(UUID id) {
        List<InformacaoAcademicaModel> informacaoAcademica = informacaoAcademicaRepository.buscarPorInformacaoAcademica(id);

        return informacaoAcademica.stream().map(informacao -> {
            informacao.setInformacao_profissional(null);
            informacao.setComunicados(null);
            return informacao;
        }).collect(Collectors.toList());
    }

    public InformacaoAcademicaModel criar(InformacaoAcademicaDTO dto) {
        EgressoModel egresso = egressoRepository.findById(dto.getEgresso_cpf())
                .orElseThrow(() -> new RuntimeException("Egresso não encontrado"));

        InformacaoAcademicaModel informacaoAcademica = new InformacaoAcademicaModel();
        BeanUtils.copyProperties(dto, informacaoAcademica);
        informacaoAcademica.setEgresso(egresso);
        return informacaoAcademicaRepository.save(informacaoAcademica);
    }

    public InformacaoAcademicaModel atualizar(UUID id, InformacaoAcademicaDTO dto) {
        Optional<InformacaoAcademicaModel> optionalInformacao = informacaoAcademicaRepository.findById(id);
        if (optionalInformacao.isPresent()) {
            InformacaoAcademicaModel informacaoAcademica = optionalInformacao.get();
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
            return informacaoAcademicaRepository.save(informacaoAcademica);
        }
        return null;
    }

    public void excluir(UUID id) {
        informacaoAcademicaRepository.deleteById(id);
    }
}
