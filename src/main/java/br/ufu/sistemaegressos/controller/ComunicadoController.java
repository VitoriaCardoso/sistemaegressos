package br.ufu.sistemaegressos.controller;

import br.ufu.sistemaegressos.dto.ComunicadoDTO;
import br.ufu.sistemaegressos.model.ComunicadoModel;
import br.ufu.sistemaegressos.service.ComunicadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunicados")
public class ComunicadoController {

    private final ComunicadoService comunicadoService;

    @Autowired
    public ComunicadoController(ComunicadoService comunicadoService) {
        this.comunicadoService = comunicadoService;
    }

    @GetMapping
    public List<ComunicadoModel> listarTodos() {
        return comunicadoService.listarTodos();
    }

    @GetMapping("/filtro")
    public List<ComunicadoModel> buscarPorFiltro(
            @RequestParam(required = false) String cursoDestino,
            @RequestParam(required = false) String nivelCursoDestino,
            @RequestParam(required = false) Boolean paraTodos) {
        return comunicadoService.buscarPorFiltro(cursoDestino, nivelCursoDestino, paraTodos);
    }

    @PostMapping
    public ComunicadoModel criarComunicado(@RequestBody ComunicadoDTO comunicadoDTO) {
        return comunicadoService.criarComunicado(comunicadoDTO);
    }
}
