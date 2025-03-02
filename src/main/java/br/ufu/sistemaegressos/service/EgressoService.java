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
        egresso.setData_atualizacao(new Timestamp(System.currentTimeMillis()));
        return egressoRepository.save(egresso);
    }

    public EgressoModel atualizar(String cpf, EgressoAtualizarDTO egressoDTO) {
        Optional<EgressoModel> optionalEgresso = egressoRepository.findById(cpf);
        if (optionalEgresso.isPresent()) {
            EgressoModel egresso = optionalEgresso.get();
            atualizarCamposEgresso(egresso, egressoDTO);
            egresso.setData_atualizacao(new Timestamp(System.currentTimeMillis()));
            return egressoRepository.save(egresso);
        }
        return null;
    }

    public void excluir(String cpf) {
        egressoRepository.deleteById(cpf);
    }

    private void atualizarCamposEgresso(EgressoModel egresso, EgressoAtualizarDTO egressoAtualizarDTO) {
        Optional.ofNullable(egressoAtualizarDTO.getNome_social()).ifPresent(egresso::setNome_social);
        Optional.ofNullable(egressoAtualizarDTO.getEmail_secundario()).ifPresent(egresso::setEmail_secundario);
        Optional.ofNullable(egressoAtualizarDTO.getTelefone()).ifPresent(egresso::setTelefone);
        Optional.ofNullable(egressoAtualizarDTO.getTelefone_secundario()).ifPresent(egresso::setTelefone_secundario);
        Optional.ofNullable(egressoAtualizarDTO.getLink_lattes()).ifPresent(egresso::setLink_lattes);
        Optional.ofNullable(egressoAtualizarDTO.getLink_orcid()).ifPresent(egresso::setLink_orcid);
        Optional.ofNullable(egressoAtualizarDTO.getLink_linkedin()).ifPresent(egresso::setLink_linkedin);
    }

    public List<EgressoModel> buscarPorFiltro(String nome, String cpf, String campus,
                                              String nomeCurso, String codigoCurso,
                                              String titulacao, String dataIngresso,
                                              String dataConclusao) {
        return egressoRepository.buscarPorFiltro(nome, cpf, nomeCurso, titulacao, dataIngresso, dataConclusao);
    }
}
