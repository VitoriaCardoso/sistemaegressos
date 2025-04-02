package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.InformacaoProfissionalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InformacaoProfissionalRepository extends JpaRepository<InformacaoProfissionalModel, UUID> {
    @Query("SELECT ip FROM InformacaoProfissionalModel ip " +
            "JOIN ip.informacao_academica ia " +
            "JOIN ia.egresso e " +
            "WHERE e.cpf = :cpf")
    List<InformacaoProfissionalModel> buscarPorEgresso(String cpf);
}