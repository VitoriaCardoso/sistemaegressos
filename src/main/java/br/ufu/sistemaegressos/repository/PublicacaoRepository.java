package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.PublicacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PublicacaoRepository extends JpaRepository<PublicacaoModel, UUID> {
    @Query("SELECT p FROM PublicacaoModel p WHERE p.informacao_academica.egresso.cpf = :cpf")
    List<PublicacaoModel> buscarPublicacaoPorEgresso(String cpf);
}
