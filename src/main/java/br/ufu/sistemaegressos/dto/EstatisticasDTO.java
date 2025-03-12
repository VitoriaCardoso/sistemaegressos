package br.ufu.sistemaegressos.dto;

import br.ufu.sistemaegressos.model.DepoimentoModel;

import java.util.List;

public class EstatisticasDTO {
    private long totalEgressos;
    private List<DepoimentoModel> totalDepoimentos;

    public EstatisticasDTO(long totalEgressos,  List<DepoimentoModel> totalDepoimentos) {
        this.totalEgressos = totalEgressos;
        this.totalDepoimentos = totalDepoimentos;
    }

    public long getTotalEgressos() {
        return totalEgressos;
    }

    public void setTotalEgressos(long totalEgressos) {
        this.totalEgressos = totalEgressos;
    }

    public List<DepoimentoModel> getTotalDepoimentos() {
        return totalDepoimentos;
    }

    public void setTotalDepoimentos(List<DepoimentoModel> totalDepoimentos) {
        this.totalDepoimentos = totalDepoimentos;
    }
}
