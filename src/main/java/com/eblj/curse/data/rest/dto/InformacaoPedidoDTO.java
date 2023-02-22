package com.eblj.curse.data.rest.dto;

import com.eblj.curse.data.domain.entities.ItemPedido;

import java.math.BigDecimal;
import java.util.List;


public class InformacaoPedidoDTO {

  private  Integer codigo;
  private String cpf;
  private String nomeCliente;
  private BigDecimal totalPedido;
  private String DataPedido ;
  private String status;
  private List<InformacaoItemPedidoDTO> items;

  public InformacaoPedidoDTO(){}

  public InformacaoPedidoDTO(Integer codigo, String cpf, String nomeCliente, BigDecimal totalPedido, String dataPedido, List<InformacaoItemPedidoDTO> items,String status) {
    this.codigo = codigo;
    this.cpf = cpf;
    this.nomeCliente = nomeCliente;
    this.totalPedido = totalPedido;
    DataPedido = dataPedido;
    this.items = items;
    this.status = status;
  }

  public String getDataPedido() {
    return DataPedido;
  }

  public void setDataPedido(String dataPedido) {
    DataPedido = dataPedido;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public BigDecimal getTotalPedido() {
    return totalPedido;
  }

  public void setTotalPedido(BigDecimal totalPedido) {
    this.totalPedido = totalPedido;
  }

  public List<InformacaoItemPedidoDTO> getItems() {
    return items;
  }

  public void setItems(List<InformacaoItemPedidoDTO> items) {
    this.items = items;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


}
