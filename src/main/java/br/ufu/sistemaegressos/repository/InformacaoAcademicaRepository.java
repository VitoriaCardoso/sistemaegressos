package br.ufu.sistemaegressos.repository;

import br.ufu.sistemaegressos.model.InformacaoAcademicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InformacaoAcademicaRepository extends JpaRepository<InformacaoAcademicaModel, String> {

    @Query("SELECT ia FROM InformacaoAcademicaModel ia WHERE ia.egresso.cpf = :cpf")
    List<InformacaoAcademicaModel> buscarPorEgresso(String cpf);

    @Query("SELECT i FROM InformacaoAcademicaModel i WHERE i.nome_curso = :nomeCurso")
    List<InformacaoAcademicaModel> buscarPorNomeCurso(String nomeCurso);
}
