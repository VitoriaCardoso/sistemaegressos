package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.EgressoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EgressoRepository extends JpaRepository<EgressoModel, String> {

    @Query("SELECT e FROM EgressoModel e " +
            "JOIN InformacaoAcademicaModel ia ON e.cpf = ia.egresso.cpf " +
            "WHERE (COALESCE(:nome, '') = '' OR e.nome LIKE %:nome%) " +
            "AND (COALESCE(:cpf, '') = '' OR e.cpf = :cpf) " +
            "AND (COALESCE(:nomeCurso, '') = '' OR ia.nome_curso LIKE %:nomeCurso%) " +
            "AND (COALESCE(:titulacao, '') = '' OR ia.titulacao = :titulacao) " +
            "AND (COALESCE(:dataIngresso, '') = '' OR ia.data_ingresso = :dataIngresso) " +
            "AND (COALESCE(:dataConclusao, '') = '' OR ia.data_conclusao = :dataConclusao)")
    List<EgressoModel> buscarPorFiltro(String nome, String cpf, String nomeCurso,
                                       String titulacao, String dataIngresso, String dataConclusao);
}
