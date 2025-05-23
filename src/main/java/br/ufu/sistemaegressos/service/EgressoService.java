package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.EgressoAtualizarDTO;
import br.ufu.sistemaegressos.dto.EgressoCriarDTO;
import br.ufu.sistemaegressos.exceptions.ResourceNotFoundException;
import br.ufu.sistemaegressos.model.EgressoModel;
import br.ufu.sistemaegressos.repository.EgressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<EgressoModel> egressos = egressoRepository.findAll();
        if (egressos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum egresso encontrado.");
        }
        return egressos;
    }

    public Optional<EgressoModel> buscarPeloCPF(String cpf) {
        Optional<EgressoModel> egresso = egressoRepository.findById(cpf);

        if (egresso.isEmpty()) {
            throw new ResourceNotFoundException("Egresso não encontrado com o CPF: " + cpf);
        }

        return egresso;
    }

    public EgressoModel criar(EgressoCriarDTO egressoDTO) {
        EgressoModel egresso = new EgressoModel();
        BeanUtils.copyProperties(egressoDTO, egresso);
        egresso.setData_atualizacao(LocalDate.now());
        return egressoRepository.save(egresso);
    }

    public EgressoModel atualizar(String cpf, EgressoAtualizarDTO egressoDTO) {
        Optional<EgressoModel> optionalEgresso = egressoRepository.findById(cpf);
        if (optionalEgresso.isPresent()) {
            EgressoModel egresso = optionalEgresso.get();
            atualizarCamposEgresso(egresso, egressoDTO);
            egresso.setData_atualizacao(LocalDate.now());
            return egressoRepository.save(egresso);
        } else {
            throw new ResourceNotFoundException("Egresso não encontrado com o CPF: " + cpf);
        }
    }

    public void excluir(String cpf) {
        if (!egressoRepository.existsById(cpf)) {
            throw new ResourceNotFoundException("Egresso não encontrado para exclusão com o CPF: " + cpf);
        }
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
                                              String curso,
                                              String titulacao, LocalDate dataIngresso,
                                              LocalDate dataConclusao) {
        List<EgressoModel> resultado = egressoRepository.buscarPorFiltro(nome, cpf, campus, curso, titulacao, dataIngresso, dataConclusao);

        if (resultado.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum egresso encontrado com os filtros aplicados.");
        }

        return resultado;
    }
}
