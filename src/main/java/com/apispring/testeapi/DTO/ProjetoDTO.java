package com.apispring.testeapi.DTO;


import com.apispring.testeapi.Enum.Classificacao;
import com.apispring.testeapi.Enum.Status;
import com.apispring.testeapi.Model.Membro;
import com.apispring.testeapi.Model.Projeto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record ProjetoDTO(Optional<Long> id,
                         String nome,
                         LocalDate dataInicio,
                         String gerente,
                         LocalDate previsaoTermino,
                         LocalDate realTermino,
                         double orcamentoTotal,
                         String descricao,
                         Status status,
                         Classificacao classificacao,
                         List<Membro> membros) {

    public ProjetoDTO(Projeto projeto) {
        this(
                Optional.of(projeto.getId()),
                projeto.getNome(),
                projeto.getDataInicio(),
                projeto.getGerente(),
                projeto.getPrevisaoTermino(),
                projeto.getRealTermino(),
                projeto.getOrcamentoTotal(),
                projeto.getDescricao(),
                projeto.getStatus(),
                projeto.getClassificacao(),
                projeto.getMembro());
    }
}
