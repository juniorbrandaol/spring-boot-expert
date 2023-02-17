package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Marca;

import java.util.List;

public interface MarcaService {

    Marca salvar(Marca marca);
    Marca getById(Integer id);
    List<Marca> findAll();
    void delete(Integer id);
    List<Marca> find(Marca filtro);
    void update(Integer id, Marca marca);

}
