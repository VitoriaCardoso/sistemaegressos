package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.ComunicadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComunicadoRepository extends JpaRepository<ComunicadoModel, UUID> {
    @Query("SELECT c FROM ComunicadoModel c WHERE (:cursoDestino IS NULL OR c.curso_destino = :cursoDestino) AND (:nivelCursoDestino IS NULL OR c.nivel_curso_destino = :nivelCursoDestino) AND (:paraTodos IS NULL OR c.para_todos = :paraTodos)")
    List<ComunicadoModel> buscarComunicadosFiltrados(String cursoDestino, String nivelCursoDestino, Boolean paraTodos);

}
