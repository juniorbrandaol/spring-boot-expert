package com.eblj.curse.data.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="cartao")
public class Cartao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="numero",nullable = false,unique = true)
    private String numero;
    @Column(name="tipo",nullable = false,unique = false)
    private String tipo;
    @Column(name="bandeira",nullable = false,unique = false)
    private String bandeira;
    @Column(name="validade",nullable = false,unique = false)
    private String validade;
    @Column(name="cvc",nullable = false,unique = false)
    private Integer cvc;

    public Cartao(){}

    public Cartao(Integer id, String numero, String tipo, String bandeira, String validade, Integer cvc) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.bandeira = bandeira;
        this.validade = validade;
        this.cvc = cvc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public Integer getCvc() {
        return cvc;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", bandeira='" + bandeira + '\'' +
                ", validade='" + validade + '\'' +
                ", cvc=" + cvc +
                '}';
    }
}
