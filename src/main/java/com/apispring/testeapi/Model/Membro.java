package com.apispring.testeapi.Model;

import com.apispring.testeapi.DTO.MembroDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(of = "id")
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "funcionario")
    private Boolean funcionario;

    public Membro() {
    }

    public Membro(Long id, String nome, String cargo, Boolean funcionario) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.funcionario = funcionario;
    }

    public Membro(MembroDTO membroDTO){
        this.nome = membroDTO.nome();
        this.cargo = membroDTO.cargo();
        this.funcionario = membroDTO.funcionario();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Boolean getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Boolean funcionario) {
        this.funcionario = funcionario;
    }
}