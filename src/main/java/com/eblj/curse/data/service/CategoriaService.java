package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria salvar(Categoria categoria);
    Categoria findById(Integer id);
    List<Categoria> findAll();
    void delete(Integer id);
    void update(Integer id,Categoria categoria);
}
