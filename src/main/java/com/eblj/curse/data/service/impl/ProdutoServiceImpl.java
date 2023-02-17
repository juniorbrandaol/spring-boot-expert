package com.eblj.curse.data.service.impl;


import com.eblj.curse.data.domain.entities.Produto;
import com.eblj.curse.data.domain.repository.ProdutoRepository;
import com.eblj.curse.data.service.ProdutoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository){
        this.repository= repository;
    }
    @Override
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public Produto getById(Integer id){
        return  repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado") );
    }
    @Override
    public List<Produto> findAll(){
        return  repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .map( produto -> {
                    repository.delete(produto);
                    return Void.TYPE;
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado"));
    }

    /*
     Este método lista todos os clientes em única chamada, bem como lista clientes com
     passagem de paramentros.
     */
    @Override
    public List<Produto> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//qualquer posisao que encontrar a string ele retorna

        Example example = Example.of(filtro,matcher);
        return repository.findAll(example);
    }


    public void update(Integer id,Produto produto){
        repository.findById(id)
                .map( newProduto -> {
                  //  produto.setId(newProduto.getId()); //outra forma
                    newProduto.setDescricao(produto.getDescricao());
                    newProduto.setPreco(produto.getPreco());
                    repository.save(newProduto);
                    return newProduto;
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Erro ao atualizar"));
    }


}
