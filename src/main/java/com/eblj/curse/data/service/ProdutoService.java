package com.eblj.curse.data.service;


import com.eblj.curse.data.domain.entities.Produto;
import java.util.List;

public interface ProdutoService {
      Produto salvar(Produto produto);
     Produto getById(Integer id);
     List<Produto> findAll();
     void delete(Integer id);
     List<Produto> find(Produto filtro);
     void update(Integer id,Produto produto);
}
