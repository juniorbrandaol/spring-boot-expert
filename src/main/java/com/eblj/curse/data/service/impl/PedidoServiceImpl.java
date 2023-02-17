package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Cliente;
import com.eblj.curse.data.domain.entities.ItemPedido;
import com.eblj.curse.data.domain.entities.Pedido;
import com.eblj.curse.data.domain.entities.Produto;
import com.eblj.curse.data.domain.repository.ClienteRepository;
import com.eblj.curse.data.domain.repository.ItemPedidoRepository;
import com.eblj.curse.data.domain.repository.PedidoRepository;
import com.eblj.curse.data.domain.repository.ProdutoRepository;
import com.eblj.curse.data.exception.RegraNegocioException;
import com.eblj.curse.data.rest.dto.ItemPedidoDTO;
import com.eblj.curse.data.rest.dto.PedidoDTO;
import com.eblj.curse.data.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository Pedidorepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoServiceImpl(PedidoRepository Pedidorepository,ClienteRepository clienteRepository,
                             ProdutoRepository produtoRepository,ItemPedidoRepository itemPedidoRepository){
        this.Pedidorepository = Pedidorepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente =  clienteRepository.findById(idCliente).
                orElseThrow(()-> new RegraNegocioException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        List<ItemPedido> itemsPedido = processarItems(pedido,dto.getItems());
        Pedidorepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItems(itemsPedido);
        return pedido;
    }

    private List<ItemPedido> processarItems(Pedido pedido,List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Lista de items vazia");
        }
        return items
                .stream()
                .map( dto ->{

                 Integer idProduto = dto.getProduto();
                 Produto produto = produtoRepository.findById(idProduto)
                         .orElseThrow(()-> new RegraNegocioException("Produto não encontrado!"));

                 ItemPedido itemPedido = new ItemPedido();
                 itemPedido.setQuantidade(dto.getQuantidade());
                 itemPedido.setPedido(pedido);
                 itemPedido.setProduto(produto);
                 return itemPedido;
        }).collect(Collectors.toList());
    }
}
