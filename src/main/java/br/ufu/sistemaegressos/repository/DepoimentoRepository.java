package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.DepoimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepoimentoRepository extends JpaRepository<DepoimentoModel, String> {
    @Query("SELECT d FROM DepoimentoModel d WHERE d.informacao_academica.egresso.cpf = :cpf")
    List<DepoimentoModel> buscarPorEgresso(String cpf);
}
