package com.apispring.testeapi.Controller;

import com.apispring.testeapi.Service.ProjetoService;
import com.apispring.testeapi.DTO.ProjetoDTO;
import com.apispring.testeapi.Model.Projeto;
import com.apispring.testeapi.Service.MembroService;
import com.apispring.testeapi.Utils.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private MembroService membroService;

    @GetMapping("buscar")
    public ResponseEntity getAllProjeto(){

        List<ProjetoDTO> projetoDto = projetoService.getAllProjeto().stream().map(ProjetoDTO::new).toList();

        if (!projetoDto.isEmpty()){
            return ResponseEntity.ok(projetoDto);
        }

        return Exceptions.returnErro("Não foi encontrado nenhum projeto cadastrado.");
    }


    @GetMapping("buscar/{id}")
    public ResponseEntity getProjeto(@PathVariable("id") Long id){
        if (this.projetoService.verificaSeExisteProjeto(id)){
            return ResponseEntity.ok(this.projetoService.buscaProjeto(id));
        }

        return Exceptions.returnErro("Projeto não encontrado.");
    }

    @PostMapping("cadastrar")
    public ResponseEntity cadastrarProjeto(@RequestBody ProjetoDTO projetoDTO){

        Projeto projeto = new Projeto(projetoDTO);

        if (!projeto.getNome().isEmpty() && projetoService.verificaSeExisteMembro(projetoDTO.membros())){
            projetoService.cadastrarOuAtualizarProjeto(projeto);

            return ResponseEntity.ok(projeto);
        }

        return Exceptions.returnErro("Não foi possível cadastrar o projeto");
    }

    @PutMapping("atualizar")
    public ResponseEntity atualizarProjeto(@RequestBody ProjetoDTO projetoDTO){

        if (projetoService.verificaSeExisteProjeto(projetoDTO.id().isPresent() ? projetoDTO.id().get() : null)
                && projetoService.verificaSeExisteMembro(projetoDTO.membros())){
            Projeto projeto = new Projeto(projetoDTO);

            projeto.setId(projetoDTO.id().get());

            return ResponseEntity.ok(projetoService.cadastrarOuAtualizarProjeto(projeto));
        }

        return Exceptions.returnErro("Não foi possível atualizar o projeto");
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity excluirProjeto(@PathVariable("id") Long id){
        Optional<Projeto> projeto = this.projetoService.buscaProjeto(id);

        if (!projetoService.validaAntesDaExclusao(projeto)){
            projetoService.excluirProjeto(id);

            return ResponseEntity.ok("Projeto excluído");
        }

        return Exceptions.returnErro("Não foi possível excluir o projeto");
    }
}

