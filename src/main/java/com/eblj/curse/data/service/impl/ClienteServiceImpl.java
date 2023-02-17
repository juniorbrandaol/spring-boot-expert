package com.eblj.curse.data.service.impl;

import com.eblj.curse.data.domain.entities.Cliente;
import com.eblj.curse.data.domain.repository.ClienteRepository;
import com.eblj.curse.data.service.ClienteService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private  ClienteRepository repository;
    public ClienteServiceImpl(ClienteRepository repository){
        this.repository = repository;
    }
    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente getClienteById(Integer id) {
        return repository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado") );
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .map( cliente ->{ repository.delete(cliente);
                    return cliente;
                })
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
    }

    @Override
    public void update(Integer id, Cliente cliente) {
        repository.findById(id).
                map(newCliente ->{
                    //  cliente.setId(newCliente.getId()); //outra forma
                    newCliente.setNome(cliente.getNome());
                    newCliente.setCpf(cliente.getCpf());
                    repository.save(newCliente);
                    return  newCliente;
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Erro ao atualizar "));
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Cliente> find(Cliente clienteFiltro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);//qualquer posisao que encontrar a string ele retorna

        Example example = Example.of(clienteFiltro,matcher);
        return repository.findAll(example);
    }
}
