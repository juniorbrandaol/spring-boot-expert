package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Fornecedor;
import com.eblj.curse.data.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService service;

    public FornecedorController(FornecedorService service){
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor save( @RequestBody Fornecedor fornecedor){
        return service.save(fornecedor);
    }

    @GetMapping("{id}")
    public Fornecedor getFornecedorById(@PathVariable Integer id){
        return service.getFornecedorById(id);
    }

    @GetMapping
    public List<Fornecedor> findAll(){
        return service.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id){
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Fornecedor fornecedor){
        service.update(id,fornecedor);
    }

}
