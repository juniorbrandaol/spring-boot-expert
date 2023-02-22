package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Cliente;
import com.eblj.curse.data.domain.entities.ItemPedido;
import com.eblj.curse.data.domain.entities.Pedido;
import com.eblj.curse.data.domain.entities.Produto;
import com.eblj.curse.data.domain.enums.StatusPedido;
import com.eblj.curse.data.domain.repository.ClienteRepository;
import com.eblj.curse.data.domain.repository.ItemPedidoRepository;
import com.eblj.curse.data.domain.repository.PedidoRepository;
import com.eblj.curse.data.domain.repository.ProdutoRepository;
import com.eblj.curse.data.exception.PedidoNaoEncontradoException;
import com.eblj.curse.data.exception.RegraNegocioException;
import com.eblj.curse.data.rest.dto.ItemPedidoDTO;
import com.eblj.curse.data.rest.dto.PedidoDTO;
import com.eblj.curse.data.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service

public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,ClienteRepository clienteRepository,ProdutoRepository produtoRepository,ItemPedidoRepository itemPedidoRepository){
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }
    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(()-> new RegraNegocioException("Cliente não encontrado"));

        Pedido pedido = new Pedido();

        pedido.setDataPedido(LocalDate.now());
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setTotal(dto.getTotal());
        pedido.setCliente(cliente);

       List<ItemPedido> itemPedidos= converterItmes(pedido,dto.getItems());

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);
        return pedido;
    }

    private List<ItemPedido> converterItmes(Pedido pedido, List<ItemPedidoDTO> items){

        if(items.isEmpty()){
            throw new RegraNegocioException("Lista de items vazia");
        }
        return items
                .stream()
                .map(dto ->{
                    Integer idProduto = dto.getProduto();
                  Produto produto=  produtoRepository
                            .findById(idProduto)
                            .orElseThrow(()-> new RegraNegocioException("Produto não enconrado "+idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return  itemPedido;
                } ).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido status) {
         pedidoRepository.findById(id).map( pedido-> {
             pedido.setStatus(status);
             pedidoRepository.save(pedido);
             return pedido;
         }).orElseThrow(()-> new PedidoNaoEncontradoException());
    }


}
