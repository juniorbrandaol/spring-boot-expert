package com.eblj.curse.data.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
    @NotNull(message = "Informe o código do cliente")
    private Integer cliente;
    @NotNull(message = "O campo total do pedido é obrigatório.")
    private BigDecimal total;
    private List<ItemPedidoDTO> items;

    public PedidoDTO(){}

    public PedidoDTO(Integer cliente, BigDecimal total, List<ItemPedidoDTO> items) {
        this.cliente = cliente;
        this.total = total;
        this.items = items;

    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoDTO> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "PedidoDTO{" +
                "cliente=" + cliente +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
