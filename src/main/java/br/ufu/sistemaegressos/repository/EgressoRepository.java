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
            "AND (:courseName IS NULL OR ia.course_name LIKE %:courseName%) " +
            "AND (:registrationNumber IS NULL OR ia.registration_number = :registrationNumber) " +
            "AND (:courseLevel IS NULL OR ia.course_level = :courseLevel) " +
            "AND (:startDate IS NULL OR ia.start_date = :startDate) " +
            "AND (:endDate IS NULL OR ia.end_date = :endDate)")
    List<EgressoModel> buscarPorFiltro(@Param("nome") String nome,
                                       @Param("cpf") String cpf,
                                       @Param("campus") String campus,
                                       @Param("courseName") String courseName,
                                       @Param("registrationNumber") String registrationNumber,
                                       @Param("courseLevel") String courseLevel,
                                       @Param("startDate") LocalDate startDate,
                                       @Param("endDate") LocalDate endDate);

}
