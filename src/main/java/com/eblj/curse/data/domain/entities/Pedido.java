package com.eblj.curse.data.domain.entities;

import com.eblj.curse.data.domain.enums.StatusPedido;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="pedido")
public class Pedido  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @Column(name="data_pedido")
    private LocalDate dataPedido;

    @Column(name="total",precision = 20,scale = 2)
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    public Pedido(){}

    public Pedido(Integer id, Cliente cliente, LocalDate dataPedido, BigDecimal total, List<ItemPedido> itens, StatusPedido status) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.total = total;
        this.itens = itens;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public LocalDate getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                ", status=" + status +
                ", itens=" + itens +
                '}';
    }
}
