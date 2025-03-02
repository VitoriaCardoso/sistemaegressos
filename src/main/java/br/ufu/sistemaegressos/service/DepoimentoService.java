package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.repository.DepoimentoRepository;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
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

    public List<DepoimentoModel> listarTodos(){
        List<DepoimentoModel> depoimentos = depoimentoRepository.findAll();
        for (DepoimentoModel depoimento : depoimentos) {
            if (depoimento.getInformacaoAcademica() != null) {
                depoimento.getInformacaoAcademica().setInformacao_profissional(null);
                depoimento.getInformacaoAcademica().setComunicados(null);
            }
        }
        return depoimentos;
    }

    public Optional<DepoimentoModel> listarPeloId(String id) {
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
        depoimento.setData_cadastro(new Timestamp(System.currentTimeMillis()));
        Optional<InformacaoAcademicaModel> informacaoAcademica = informacaoAcademicaRepository.findById(depoimentoDTO.getMatricula_academica());
        if (informacaoAcademica.isPresent()) {
            depoimento.setInformacaoAcademica(informacaoAcademica.get());
            depoimento.getInformacaoAcademica().setComunicados(null);
            depoimento.getInformacaoAcademica().setInformacao_profissional(null);
        } else {
            throw new RuntimeException("Informação acadêmica não encontrada para a matrícula: " + depoimentoDTO.getMatricula_academica());
        }
        return depoimentoRepository.save(depoimento);
    }

    public void excluir(String id){ depoimentoRepository.deleteById(id);}

    public List<DepoimentoModel> listarPorCpfEgresso(String cpf) {
        List<DepoimentoModel> depoimentos = depoimentoRepository.buscarPorEgresso(cpf);

        return depoimentos.stream().map(depoimento -> {
            depoimento.setInformacaoAcademica(null);
            return depoimento;
        }).collect(Collectors.toList());
    }
}
