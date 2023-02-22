package com.eblj.curse.data.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

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
    @Column(name = "nome",nullable = false)
    @NotEmpty(message = "O campo nome é obrigatório.")
    @Length(min = 3, max = 45, message = "O nome deverá ter no mínimo {min} e no máximo {max} caracteres")
    private String nome;
    @Column(name="cpf", nullable = false,length = 11 , unique = true)
    @NotBlank(message = "O campo cpf é obrigatório")
    @CPF(message = "CPF não é válido.")
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
