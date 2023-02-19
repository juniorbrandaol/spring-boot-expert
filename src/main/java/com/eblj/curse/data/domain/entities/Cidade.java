package com.eblj.curse.data.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="cidade")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="nome",nullable = false,unique = true)
    private String nome;
    @Column(name="uf",nullable = false,unique = false,length = 2)
    private String uf;
    @Column(name="codigo",nullable = false,unique = false)
    private Integer codigoIBGE;

    public Cidade(){}

    public Cidade(Integer id, String nome, String uf, Integer codigoIBGE) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.codigoIBGE = codigoIBGE;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(Integer codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                ", codigoIBGE=" + codigoIBGE +
                '}';
    }
}
