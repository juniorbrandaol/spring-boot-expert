package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Estoque;
import com.eblj.curse.data.domain.repository.EstoqueRepository;
import com.eblj.curse.data.exception.RegraNegocioException;
import com.eblj.curse.data.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EstoqueServiceImpl  implements EstoqueService {
    @Autowired
    EstoqueRepository repository;

    public EstoqueServiceImpl(EstoqueRepository repository){
        this.repository = repository;
    }

    @Override
    public Estoque save(Estoque estoque) {
        return repository.save(estoque);
    }

    @Override
    public Estoque findById(Integer id) {
        return repository.findById(id).
                orElseThrow(()-> new RegraNegocioException("Produto não encontrado."));
    }

    @Override
    public List<Estoque> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id).map( estoque ->{
            repository.delete(estoque);
            return estoque;
        }).orElseThrow(()->new RegraNegocioException("Produto não encontrado."));
    }

    @Override
    public void update(Integer id, Estoque estoque) {
       repository.findById(id).map(newEstoque->{
           newEstoque.setProduto(estoque.getProduto());
           newEstoque.setQuantidade(estoque.getQuantidade());
           repository.save(newEstoque);
           return newEstoque;
       }).orElseThrow(()-> new RegraNegocioException("Produto não encontrado"));
    }

    @Override
    public void updateEstoque(Integer id, Estoque estoque) {
        repository.findById(id).map(newEstoque->{
            newEstoque.setQuantidade(estoque.getQuantidade());
            repository.save(newEstoque);
            return newEstoque;
        }).orElseThrow(()-> new RegraNegocioException("Produto não encontrado"));
    }
}
