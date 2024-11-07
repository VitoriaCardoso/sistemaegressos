package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.DepoimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepoimentoRepository extends JpaRepository<DepoimentoModel, String> {
}
