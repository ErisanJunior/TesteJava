package com.apispring.testeapi.Service;

import com.apispring.testeapi.Model.Membro;
import com.apispring.testeapi.Repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    public Optional<Membro> buscaMembro(Long id){
        return this.membroRepository.findById(id);
    }

    public List<Membro> getAllMembro() {
        return this.membroRepository.findAll();
    }

    public Membro cadastrarOuAtualizarMembro(Membro membro){
        this.membroRepository.save(membro);
        return membro;
    }

    public void excluirMembro(Long id){
        this.membroRepository.deleteById(id);
    }

    public Boolean verificaSeExisteMembro(Long id){
        return this.membroRepository.existsById(id);
    }
}