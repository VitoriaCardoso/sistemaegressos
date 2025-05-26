package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.dto.EgressoDepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DepoimentoRepository extends JpaRepository<DepoimentoModel, UUID> {
    @Query("SELECT d FROM DepoimentoModel d WHERE d.informacao_academica.egresso.cpf = :cpf")
    List<DepoimentoModel> buscarPorEgresso(String cpf);

    @Query("""
        SELECT new br.ufu.sistemaegressos.dto.EgressoDepoimentoDTO(
            e.nome, ia.course_name, ia.course_level, ia.campus, ip.texto_depoimento)
        FROM DepoimentoModel ip
        JOIN InformacaoAcademicaModel ia ON ip.informacao_academica.id = ia.id
        JOIN EgressoModel e ON ia.egresso.cpf = e.cpf
    """)
    List<EgressoDepoimentoDTO> buscarDepoimentos();
}
