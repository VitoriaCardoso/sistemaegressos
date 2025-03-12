package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformacaoAcademicaRepository extends JpaRepository<InformacaoAcademicaModel, String> {

    @Query("SELECT ia FROM InformacaoAcademicaModel ia WHERE ia.egresso.cpf = :cpf")
    List<InformacaoAcademicaModel> buscarPorEgresso(String cpf);

    @Query("SELECT i FROM InformacaoAcademicaModel i WHERE i.nome_curso = :nomeCurso")
    List<InformacaoAcademicaModel> buscarPorNomeCurso(String nomeCurso);

    @Query("SELECT COUNT(i) FROM InformacaoAcademicaModel i WHERE i.nome_curso = :nomeCurso AND i.campus = :campus")
    Long contarEstudantesPorCursoECampus(@Param("curso") String nomeCurso, @Param("campus") String campus);

    @Query("SELECT COUNT(i) FROM InformacaoAcademicaModel i WHERE i.ativo = true")
    Long contarEstudantesAtivos();

    @Query("SELECT COUNT(i) FROM InformacaoAcademicaModel i WHERE i.ativo = false")
    Long contarEstudantesInativos();

    @Query("SELECT i.nome_curso, i.nome_instituicao, COUNT(i) FROM InformacaoAcademicaModel i GROUP BY i.nome_curso, i.nome_instituicao")
    List<Object[]> contarEstudantesPorCursoECampus();


    @Query("SELECT i.campus, COUNT(i) FROM InformacaoAcademicaModel i GROUP BY i.nomeCurso")
    List<Object[]> contarEstudantesPorCampus();

    @Query("SELECT i.titulacao, COUNT(i) FROM InformacaoAcademicaModel i GROUP BY i.titulacao")
    List<Object[]> contarEstudantesPorTitulacao();

}
