package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.PublicacaoDTO;
import br.ufu.sistemaegressos.model.PublicacaoModel;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.repository.PublicacaoRepository;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final InformacaoAcademicaRepository informacaoAcademicaRepository;

    @Autowired
    public PublicacaoService(PublicacaoRepository publicacaoRepository, InformacaoAcademicaRepository informacaoAcademicaRepository) {
        this.publicacaoRepository = publicacaoRepository;
        this.informacaoAcademicaRepository = informacaoAcademicaRepository;
    }

    public List<PublicacaoModel> buscarPorEgresso(String cpf) {
        List<PublicacaoModel> publicacoes = publicacaoRepository.buscarPublicacaoPorEgresso(cpf);

        return publicacoes.stream().map(publicacao -> {
            InformacaoAcademicaModel informacaoAcademica = publicacao.getInformacao_academica();
            informacaoAcademica.setInformacao_profissional(null);
            informacaoAcademica.setComunicados(null);
            return publicacao;
        }).collect(Collectors.toList());
    }

    public PublicacaoModel criar(PublicacaoDTO publicacaoDTO) {
        InformacaoAcademicaModel informacaoAcademica = informacaoAcademicaRepository
                .findById(publicacaoDTO.getMatricula_academica())
                .orElseThrow(() -> new RuntimeException("Informação acadêmica não encontrada"));

        PublicacaoModel publicacao = new PublicacaoModel();
        BeanUtils.copyProperties(publicacaoDTO, publicacao);
        publicacao.setInformacao_academica(informacaoAcademica);
        publicacao = publicacaoRepository.save(publicacao);

        informacaoAcademica = publicacao.getInformacao_academica();
        informacaoAcademica.setInformacao_profissional(null);
        informacaoAcademica.setComunicados(null);

        return publicacao;
    }

    public PublicacaoModel atualizar(String id, PublicacaoDTO publicacaoDTO) {
        Optional<PublicacaoModel> optionalPublicacao = publicacaoRepository.findById(id);
        if (optionalPublicacao.isPresent()) {
            PublicacaoModel publicacao = optionalPublicacao.get();
            BeanUtils.copyProperties(publicacaoDTO, publicacao);
            return publicacaoRepository.save(publicacao);
        }
        return null;
    }

    public void excluir(String id) {
        publicacaoRepository.deleteById(id);
    }
}
