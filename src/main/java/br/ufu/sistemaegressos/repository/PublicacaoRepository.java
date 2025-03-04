package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.PublicacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacaoRepository extends JpaRepository<PublicacaoModel, String> {
    @Query("SELECT p FROM PublicacaoModel p WHERE p.informacao_academica.egresso.cpf = :cpf")
    List<PublicacaoModel> buscarPublicacaoPorEgresso(String cpf);
}
