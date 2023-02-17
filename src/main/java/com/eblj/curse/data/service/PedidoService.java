package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Pedido;
import com.eblj.curse.data.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
