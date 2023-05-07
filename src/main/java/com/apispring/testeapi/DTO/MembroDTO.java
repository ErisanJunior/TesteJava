package com.apispring.testeapi.DTO;

import com.apispring.testeapi.Model.Membro;

import java.util.Optional;

public record MembroDTO(Optional<Long> id,
                        String nome,
                        String cargo,
                        Boolean funcionario) {
    public MembroDTO(Membro membro){
        this(
                Optional.of(membro.getId()),
                membro.getNome(),
                membro.getCargo(),
                membro.getFuncionario());
    }

}