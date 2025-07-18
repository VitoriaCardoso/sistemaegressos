package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.PublicacaoDTO;
import br.ufu.sistemaegressos.exceptions.ResourceNotFoundException;
import br.ufu.sistemaegressos.model.PublicacaoModel;
import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import br.ufu.sistemaegressos.repository.PublicacaoRepository;
import br.ufu.sistemaegressos.repository.InformacaoAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

        if (publicacoes.isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma publicação encontrada para o CPF: " + cpf);
        }

        return publicacoes.stream().peek(publicacao -> {
            InformacaoAcademicaModel informacaoAcademica = publicacao.getInformacao_academica();
            informacaoAcademica.setComunicados(null);
            informacaoAcademica.setEgresso(null);
        }).collect(Collectors.toList());
    }

    public PublicacaoModel criar(PublicacaoDTO publicacaoDTO) {
        InformacaoAcademicaModel informacaoAcademica = informacaoAcademicaRepository
                .findById(publicacaoDTO.getId_informacao_academica())
                .orElseThrow(() -> new ResourceNotFoundException("Informação acadêmica não encontrada para o ID: " + publicacaoDTO.getId_informacao_academica()));

        PublicacaoModel publicacao = new PublicacaoModel();
        BeanUtils.copyProperties(publicacaoDTO, publicacao);
        publicacao.setInformacao_academica(informacaoAcademica);
        publicacao = publicacaoRepository.save(publicacao);

        informacaoAcademica = publicacao.getInformacao_academica();
        informacaoAcademica.setComunicados(null);

        return publicacao;
    }

    public PublicacaoModel buscarPorId(UUID id) {
        Optional<PublicacaoModel> optional = publicacaoRepository.findById(id);
        if (optional.isPresent()) {
            PublicacaoModel pub = optional.get();
            if (pub.getInformacao_academica() != null) {
                pub.getInformacao_academica().setComunicados(null);
                pub.getInformacao_academica().setEgresso(null);
            }
            return pub;
        } else {
            throw new ResourceNotFoundException("Publicação não encontrada para o ID: " + id);
        }
    }


    public PublicacaoModel atualizar(UUID id, PublicacaoDTO publicacaoDTO) {
        Optional<PublicacaoModel> optionalPublicacao = publicacaoRepository.findById(id);
        if (optionalPublicacao.isPresent()) {
            PublicacaoModel publicacao = optionalPublicacao.get();

            Optional.ofNullable(publicacaoDTO.getTitulo()).ifPresent(publicacao::setTitulo);
            Optional.ofNullable(publicacaoDTO.getAutores()).ifPresent(publicacao::setAutores);
            Optional.ofNullable(publicacaoDTO.getAno_publicacao()).ifPresent(publicacao::setAno_publicacao);
            Optional.ofNullable(publicacaoDTO.getVeiculo()).ifPresent(publicacao::setVeiculo);
            Optional.ofNullable(publicacaoDTO.getUrl_publicacao()).ifPresent(publicacao::setUrl_publicacao);

            Optional.ofNullable(publicacaoDTO.getId_informacao_academica()).ifPresent(idInfo -> {
                InformacaoAcademicaModel info = informacaoAcademicaRepository.findById(idInfo)
                        .orElseThrow(() -> new ResourceNotFoundException("Informação acadêmica não encontrada para o ID: " + idInfo));
                publicacao.setInformacao_academica(info);
            });

            return publicacaoRepository.save(publicacao);
        } else {
            throw new ResourceNotFoundException("Publicação não encontrada para o ID: " + id);
        }
    }

    public void excluir(UUID id) {
        if (!publicacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Publicação não encontrada para exclusão com o ID: " + id);
        }
        publicacaoRepository.deleteById(id);
    }
}
