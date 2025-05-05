package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InformacaoAcademicaRepository extends JpaRepository<InformacaoAcademicaModel, UUID> {

    @Query("SELECT ia FROM InformacaoAcademicaModel ia WHERE ia.egresso.cpf = :cpf")
    List<InformacaoAcademicaModel> buscarPorEgresso(String cpf);

    @Query("SELECT i FROM InformacaoAcademicaModel i WHERE i.course_name = :courseName")
    List<InformacaoAcademicaModel> buscarPorNomeCurso(String courseName);

    @Query("SELECT i FROM InformacaoAcademicaModel i WHERE i.course_level = :courseLevel")
    List<InformacaoAcademicaModel> buscarPorNivelCurso(String courseLevel);

    @Query("SELECT i FROM InformacaoAcademicaModel i WHERE i.course_name = :courseName AND i.course_level = :courseLevel")
    List<InformacaoAcademicaModel> buscarPorNomeENivelCurso(String courseName, String courseLevel);

    @Query("SELECT COUNT(i) FROM InformacaoAcademicaModel i WHERE i.course_name = :courseName AND i.campus = :campus")
    Long contarEstudantesPorCursoECampus(@Param("courseName") String courseName, @Param("campus") String campus);

    @Query("SELECT COUNT(i) FROM InformacaoAcademicaModel i WHERE i.ativo = true")
    Long contarEstudantesAtivos();

    @Query("SELECT COUNT(i) FROM InformacaoAcademicaModel i WHERE i.ativo = false")
    Long contarEstudantesInativos();

    @Query("SELECT i.course_name, i.campus, i.course_level, COUNT(i) FROM InformacaoAcademicaModel i GROUP BY i.course_name, i.campus, i.course_level")
    List<Object[]> contarEstudantesPorCursoECampus();

    @Query("SELECT i.campus, COUNT(i) FROM InformacaoAcademicaModel i GROUP BY i.campus")
    List<Object[]> contarEstudantesPorCampus();

    @Query("SELECT i.course_level, COUNT(i) FROM InformacaoAcademicaModel i GROUP BY i.course_level")
    List<Object[]> contarEstudantesPorTitulacao();

    @Query("SELECT ia FROM InformacaoAcademicaModel ia WHERE ia.id = :id")
    List<InformacaoAcademicaModel> buscarPorInformacaoAcademica(@Param("id")UUID id);
}
