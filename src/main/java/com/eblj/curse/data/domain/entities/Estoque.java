package com.eblj.curse.data.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="estoque")
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "produto",nullable = false,unique = true)
    private String produto;
    @Column(name="quantidade",nullable = false)
    private Integer quantidade;

    public Estoque(){}

    public Estoque(Integer id, String produto, Integer quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", produto='" + produto + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
