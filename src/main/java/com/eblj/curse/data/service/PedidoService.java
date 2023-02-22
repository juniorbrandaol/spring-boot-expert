package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Pedido;
import com.eblj.curse.data.domain.enums.StatusPedido;
import com.eblj.curse.data.rest.dto.PedidoDTO;

import java.util.Optional;


public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido status);

}
