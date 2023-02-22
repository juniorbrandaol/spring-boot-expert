package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.ItemPedido;
import com.eblj.curse.data.domain.entities.Pedido;
import com.eblj.curse.data.domain.enums.StatusPedido;
import com.eblj.curse.data.rest.dto.AtualizacaoStatusPedidoDTO;
import com.eblj.curse.data.rest.dto.InformacaoItemPedidoDTO;
import com.eblj.curse.data.rest.dto.InformacaoPedidoDTO;
import com.eblj.curse.data.rest.dto.PedidoDTO;
import com.eblj.curse.data.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    public PedidoController(){}

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save( @RequestBody @Valid PedidoDTO dto){
       Pedido pedido = service.salvar(dto);
       return pedido.getId();
    }
    @GetMapping("{id}")
    public InformacaoPedidoDTO getById( @PathVariable  Integer id){
       return service.obterPedidoCompleto(id)
               .map( p -> converter(p) )
               .orElseThrow( ()-> new
                       ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado"));
    }
    private InformacaoPedidoDTO converter(Pedido pedido){
         InformacaoPedidoDTO info = new InformacaoPedidoDTO();
         info.setCodigo(pedido.getId());
         info.setDataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
         info.setCpf(pedido.getCliente().getCpf());
         info.setNomeCliente(pedido.getCliente().getNome());
         info.setTotalPedido(pedido.getTotal());
         info.setStatus(pedido.getStatus().name());
         info.setItems(converterItem(pedido.getItens()));
         return  info;
    }

    public List<InformacaoItemPedidoDTO> converterItem(List<ItemPedido> items){
        InformacaoItemPedidoDTO info = new InformacaoItemPedidoDTO();
       if(CollectionUtils.isEmpty(items)){
           return  Collections.emptyList();
       }
       return  items.stream().map( item-> {
           info.setDescricaoProduto(item.getProduto().getDescricao());
           info.setPrecoUnitario(item.getProduto().getPreco());
           info.setQuantidade(item.getQuantidade());
           return info;
       }).collect(Collectors.toList());
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus( @PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

}
