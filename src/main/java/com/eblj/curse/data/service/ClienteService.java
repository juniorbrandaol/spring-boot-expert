package com.eblj.curse.data.service;

import com.eblj.curse.data.domain.entities.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente save(Cliente cliente);
    Cliente getClienteById(Integer id);
    void delete(Integer id);
    void update(Integer id, Cliente cliente);
    List<Cliente> findAll();
    List<Cliente> find(Cliente clienteFiltro);

}
