package com.eblj.curse.data.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column(name = "nome",nullable = false)
   private String nome;
   @Column(name = "cnpj",nullable = false,unique = true,length = 14)
   private String cnpj;
   @Column(name="telefone",nullable = false, unique = true,length = 16)
   private String telefone;

   public Fornecedor(){}

    public Fornecedor(Integer id, String nome, String cnpj, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
