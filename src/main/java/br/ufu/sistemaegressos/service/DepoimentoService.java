package br.ufu.sistemaegressos.service;

import br.ufu.sistemaegressos.dto.DepoimentoDTO;
import br.ufu.sistemaegressos.model.DepoimentoModel;
import br.ufu.sistemaegressos.repository.DepoimentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class DepoimentoService {

    private final DepoimentoRepository depoimentoRepository;

    @Autowired
    public DepoimentoService(DepoimentoRepository depoimentoRepository) {
        this.depoimentoRepository = depoimentoRepository;
    }

    public List<DepoimentoModel> listarTodos(){
        return depoimentoRepository.findAll();
    }

    public Optional<DepoimentoModel> listarPeloId(String id){
        return depoimentoRepository.findById(id);
    }

    public DepoimentoModel criarDepoimento(DepoimentoDTO depoimentoDTO){
        DepoimentoModel depoimento = new DepoimentoModel();
        BeanUtils.copyProperties(depoimentoDTO, depoimento);
        depoimento.setData_cadastro(new Timestamp(System.currentTimeMillis()));
        return depoimentoRepository.save(depoimento);
    }

    public void excluir(String id){ depoimentoRepository.deleteById(id);}

}
