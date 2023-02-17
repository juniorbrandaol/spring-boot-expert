package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Pedido;
import com.eblj.curse.data.rest.dto.PedidoDTO;
import com.eblj.curse.data.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }



}
