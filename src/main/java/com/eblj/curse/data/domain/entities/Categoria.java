package com.eblj.curse.data.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name="categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nome",nullable = false,unique = true)
    private String nome;
    @Column(name="imgPath",nullable = false)
    private String imgPath ;

    public Categoria(){}

    public Categoria(Integer id, String nome, String imgPath) {
        this.id = id;
        this.nome = nome;
        this.imgPath = imgPath;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
