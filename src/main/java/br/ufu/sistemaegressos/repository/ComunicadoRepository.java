package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.ComunicadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunicadoRepository extends JpaRepository<ComunicadoModel, String> {

    @Query("SELECT c FROM ComunicadoModel c WHERE c.curso_destino = :cursoDestino AND c.nivel_curso_destino = :nivelCursoDestino")
    List<ComunicadoModel> buscarComunicadoPorCursoEnivel(String cursoDestino, String nivelCursoDestino);

    @Query("SELECT c FROM ComunicadoModel c WHERE c.curso_destino = :cursoDestino")
    List<ComunicadoModel> buscarComunicadoPorCurso(String cursoDestino);

    @Query("SELECT c FROM ComunicadoModel c WHERE c.nivel_curso_destino = :nivelCursoDestino")
    List<ComunicadoModel> buscarComunicadosPorNivelCurso(String nivelCursoDestino);

    @Query("SELECT c FROM ComunicadoModel c WHERE c.para_todos = :paraTodos")
    List<ComunicadoModel> buscarComunicadosParaTodos(Boolean paraTodos);
}
