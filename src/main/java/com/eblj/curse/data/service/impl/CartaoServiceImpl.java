package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Cartao;
import com.eblj.curse.data.domain.repository.CartaoRepository;
import com.eblj.curse.data.exception.RegraNegocioException;
import com.eblj.curse.data.service.CartaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartaoServiceImpl implements CartaoService {

    private CartaoRepository repository;

    public CartaoServiceImpl(CartaoRepository repository){
        this.repository = repository;
    }

    @Override
    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    @Override
    public Cartao findById(Integer id) {
        return repository.findById(id).
                orElseThrow(()-> new RegraNegocioException("Cartão não encontrado."));
    }

    @Override
    public List<Cartao> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id) {
           repository.findById(id).map( cidade->{
               repository.delete(cidade);
               return cidade;
           }).orElseThrow(()-> new RegraNegocioException("Cartão não encontrado"));
    }

    @Override
    public void update(Integer id, Cartao cartao) {
          repository.findById(id).map(newCartao->{
              newCartao.setNumero(cartao.getNumero());
              newCartao.setTipo(cartao.getTipo());
              newCartao.setBandeira(cartao.getBandeira());
              newCartao.setCvc(cartao.getCvc());
              newCartao.setValidade(cartao.getValidade());
              repository.save(newCartao);
              return newCartao;
          }).orElseThrow(()-> new RegraNegocioException("Cartão não encontrado"));
    }


}
