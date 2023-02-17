package com.eblj.curse.data.service;
import com.eblj.curse.data.domain.entities.Fornecedor;

import java.util.List;

public interface FornecedorService {

    Fornecedor save(Fornecedor fornecedor);
    Fornecedor getFornecedorById(Integer id);
    List<Fornecedor> findAll();
    void delete(Integer id);
    void update(Integer id,Fornecedor fornecedor);
}
