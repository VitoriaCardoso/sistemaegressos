package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.ComunicadoDTO;
import br.ufu.sistemaegressos.model.ComunicadoModel;
import br.ufu.sistemaegressos.service.ComunicadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunicados")
@Tag(name = "Comunicados")
public class ComunicadoController {

    private final ComunicadoService comunicadoService;

    @Autowired
    public ComunicadoController(ComunicadoService comunicadoService) {
        this.comunicadoService = comunicadoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os comunicados")
    public List<ComunicadoModel> listarTodos() {
        return comunicadoService.listarTodos();
    }

    @GetMapping("/filtro")
    @Operation(
            summary = "Buscar comunicados por filtro",
            description = "Permite filtrar comunicados com base no curso de destino, nível do curso e se o comunicado é destinado a todos os egressos. Todos os filtros são opcionais."
    )
    public List<ComunicadoModel> buscarPorFiltro(
            @RequestParam(required = false) String cursoDestino,
            @RequestParam(required = false) String nivelCursoDestino,
            @RequestParam(required = false) Boolean paraTodos) {
        return comunicadoService.buscarPorFiltro(cursoDestino, nivelCursoDestino, paraTodos);
    }

    @PostMapping
    @Operation(summary = "Criar um novo comunicado")
    public ComunicadoModel criarComunicado(@RequestBody ComunicadoDTO comunicadoDTO) {
        return comunicadoService.criarComunicado(comunicadoDTO);
    }
}
