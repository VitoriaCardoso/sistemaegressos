package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.DepoimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DepoimentoRepository extends JpaRepository<DepoimentoModel, UUID> {
    @Query("SELECT d FROM DepoimentoModel d WHERE d.informacao_academica.egresso.cpf = :cpf")
    List<DepoimentoModel> buscarPorEgresso(String cpf);
}
