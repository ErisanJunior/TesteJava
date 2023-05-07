package com.apispring.testeapi.Service;


import com.apispring.testeapi.Enum.Status;
import com.apispring.testeapi.Model.Membro;
import com.apispring.testeapi.Model.Projeto;
import com.apispring.testeapi.Repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private MembroService membroService;

    public Optional<Projeto> buscaProjeto(Long id){
        return this.projetoRepository.findById(id);
    }

    public List<Projeto> getAllProjeto() {
        return this.projetoRepository.findAll();
    }

    public Projeto cadastrarOuAtualizarProjeto(Projeto projeto){
        this.projetoRepository.save(projeto);
        return projeto;
    }

    public void excluirProjeto(Long id){
        this.projetoRepository.deleteById(id);
    }

    public Boolean verificaSeExisteProjeto(Long id){
        return this.projetoRepository.existsById(id);
    }

    public boolean validaAntesDaExclusao(Optional<Projeto> projeto){
        Status status = projeto.get().getStatus();

        return status.equals(Status.EMANDAMENTO) || status.equals(Status.INICIADO) || status.equals(Status.ENCERRADO);
    }

    public boolean verificaSeExisteMembro(List<Membro> membros){

        for (Membro membro : membros) {
            if (!this.membroService.verificaSeExisteMembro(membro.getId())){
                return false;
            }
        }
        return true;
    }
}