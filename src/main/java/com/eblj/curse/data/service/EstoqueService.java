package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Estoque;

import java.util.List;

public interface EstoqueService {

    Estoque save(Estoque estoque);
    Estoque findById(Integer id);
    List<Estoque> findAll();
    void delete(Integer id);
    void update(Integer id, Estoque estoque);
    void updateEstoque(Integer id, Estoque estoque);

}
