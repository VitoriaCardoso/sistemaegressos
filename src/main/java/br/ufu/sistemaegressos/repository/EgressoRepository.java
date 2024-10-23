package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.EgressoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgressoRepository extends JpaRepository<EgressoModel, String> {
}

