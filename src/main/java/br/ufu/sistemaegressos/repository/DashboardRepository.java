package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.dto.CursoCampusTitulacaoDTO;
import br.ufu.sistemaegressos.dto.StatusEstudanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<StatusEstudanteDTO> buscarStatusEstudantesComTotal() {
        String sql = """
            SELECT ia.ativo::TEXT AS status, COUNT(*) AS total
            FROM informacao_academica ia
            GROUP BY ia.ativo

            UNION ALL

            SELECT 'TOTAL' AS status, COUNT(*) AS total
            FROM informacao_academica
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new StatusEstudanteDTO(rs.getString("status"), rs.getLong("total"))
        );
    }

    public List<StatusEstudanteDTO> buscarLevelEstudantesComTotal() {
        String sql = """
        SELECT ia.course_level AS nivel, COUNT(*) AS total
        FROM informacao_academica ia
        GROUP BY ia.course_level
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new StatusEstudanteDTO(rs.getString("nivel"), rs.getLong("total"))
        );
    }

    public List<StatusEstudanteDTO> buscarCampusEstudantesComTotal() {
        String sql = """
            SELECT ia.campus AS campus, COUNT(*) AS total
            FROM informacao_academica ia
            GROUP BY ia.campus
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new StatusEstudanteDTO(rs.getString("campus"), rs.getLong("total"))
        );
    }

    public List<CursoCampusTitulacaoDTO> buscarTotalPorCursoCampusTitulacao(String campus, String semestre, String titulacao, String curso) {
        StringBuilder sql = new StringBuilder("""
        SELECT 
            i.course_name AS curso,
            i.campus AS campus,
            i.course_level AS titulacao,
            i.end_semester || '/' || i.end_year AS semestre,
            COUNT(*) AS total
        FROM informacao_academica i
        WHERE 1 = 1
    """);

        List<Object> params = new ArrayList<>();

        if (campus != null && !campus.isBlank()) {
            sql.append(" AND i.campus = ? ");
            params.add(campus);
        }

        if (semestre != null && !semestre.isBlank()) {
            sql.append(" AND (i.end_semester || '/' || i.end_year) = ? ");
            params.add(semestre);
        }

        if (titulacao != null && !titulacao.isBlank()) {
            sql.append(" AND i.course_level = ? ");
            params.add(titulacao);
        }

        if (curso != null && !curso.isBlank()) {
            sql.append(" AND i.course_name = ? ");
            params.add(curso);
        }

        sql.append(" GROUP BY i.course_name, i.campus, i.course_level, i.end_semester, i.end_year ");
        sql.append(" ORDER BY i.campus, i.course_level, i.course_name, semestre ");

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
                new CursoCampusTitulacaoDTO(
                        rs.getString("curso"),
                        rs.getString("campus"),
                        rs.getString("titulacao"),
                        rs.getString("semestre"),
                        rs.getLong("total")
                )
        );
    }

}
