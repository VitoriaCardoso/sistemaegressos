package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.EgressoAtualizarDTO;
import br.ufu.sistemaegressos.dto.EgressoCriarDTO;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.repository.EgressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class EgressoService {
    private final EgressoRepository egressoRepository;

    @Autowired
    public EgressoService(EgressoRepository egressoRepository) {
        this.egressoRepository = egressoRepository;
    }

    public List<EgressoModel> buscarTodos() {
        return egressoRepository.findAll();
    }

    public Optional<EgressoModel> buscarPeloCPF(String cpf) {
        return egressoRepository.findById(cpf);
    }

    public EgressoModel criar(EgressoCriarDTO egressoDTO) {
        EgressoModel egresso = new EgressoModel();
        BeanUtils.copyProperties(egressoDTO, egresso);
        egresso.setDataAtualizacao(new Timestamp(System.currentTimeMillis()));
        return egressoRepository.save(egresso);
    }

    public EgressoModel atualizar(String cpf, EgressoAtualizarDTO egressoDTO) {
        Optional<EgressoModel> optionalEgresso = egressoRepository.findById(cpf);
        if (optionalEgresso.isPresent()) {
            EgressoModel egresso = optionalEgresso.get();
            atualizarCamposEgresso(egresso, egressoDTO);
            egresso.setDataAtualizacao(new Timestamp(System.currentTimeMillis()));
            return egressoRepository.save(egresso);
        }
        return null;
    }

    public void excluir(String cpf) {
        egressoRepository.deleteById(cpf);
    }

    private void atualizarCamposEgresso(EgressoModel egresso, EgressoAtualizarDTO egressoAtualizarDTO) {
        Optional.ofNullable(egressoAtualizarDTO.getNomeSocial()).ifPresent(egresso::setNomeSocial);
        Optional.ofNullable(egressoAtualizarDTO.getEmailSecundario()).ifPresent(egresso::setEmailSecundario);
        Optional.ofNullable(egressoAtualizarDTO.getTelefone()).ifPresent(egresso::setTelefone);
        Optional.ofNullable(egressoAtualizarDTO.getTelefoneSecundario()).ifPresent(egresso::setTelefoneSecundario);
        Optional.ofNullable(egressoAtualizarDTO.getIdPessoa()).ifPresent(egresso::setIdPessoa);
        Optional.ofNullable(egressoAtualizarDTO.getLinkLattes()).ifPresent(egresso::setLinkLattes);
        Optional.ofNullable(egressoAtualizarDTO.getLinkOrcid()).ifPresent(egresso::setLinkOrcid);
        Optional.ofNullable(egressoAtualizarDTO.getLinkLinkedin()).ifPresent(egresso::setLinkLinkedin);
    }
}
