package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.EgressoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EgressoRepository extends JpaRepository<EgressoModel, String> {

    @Query("SELECT e FROM EgressoModel e JOIN InformacaoAcademicaModel ia ON e.cpf = ia.egresso.cpf " +
            "WHERE (:nome IS NULL OR e.nome LIKE %:nome%) " +
            "AND (:cpf IS NULL OR e.cpf = :cpf) " +
            "AND (:campus IS NULL OR ia.campus LIKE %:campus%) " +
            "AND (:nomeCurso IS NULL OR ia.nome_curso LIKE %:nomeCurso%) " +
            "AND (:codigoCurso IS NULL OR ia.codigo_curso = :codigoCurso) " +
            "AND (:titulacao IS NULL OR ia.titulacao = :titulacao) " +
            "AND (:dataIngresso IS NULL OR ia.data_ingresso = :dataIngresso) " +
            "AND (:dataConclusao IS NULL OR ia.data_conclusao = :dataConclusao)")
    List<EgressoModel> buscarPorFiltro(@Param("nome") String nome,
                                       @Param("cpf") String cpf,
                                       @Param("campus") String campus,
                                       @Param("nomeCurso") String nomeCurso,
                                       @Param("codigoCurso") String codigoCurso,
                                       @Param("titulacao") String titulacao,
                                       @Param("dataIngresso") LocalDate dataIngresso,
                                       @Param("dataConclusao") LocalDate dataConclusao);

}
