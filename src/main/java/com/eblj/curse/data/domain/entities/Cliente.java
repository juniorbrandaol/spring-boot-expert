package com.eblj.curse.data.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cliente")//opcional, já que o nome da tabela é o mesmo da classe
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")//opcional, já que o nome do atributo é o mesmo da classe
    private Integer id;
    @Column( nullable = false)
    private String nome;
    @Column(name="cpf", nullable = false,length = 11 , unique = true)
    private String cpf;

    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Pedido> pedidos;

    public Cliente(){
    }

    public Cliente(Integer id, String nome,String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf=cpf;
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

    public String getCpf(){
        return  cpf;
    }

    public void setCpf(String cpf){
        this.cpf= cpf;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\''+
                ", CPF='" + cpf + '\''+
                '}';
    }
}
