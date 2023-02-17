package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Produto;
import com.eblj.curse.data.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto){
       return service.salvar(produto);
    }

    @GetMapping("{codigoProduto}")
    public Produto getById(@PathVariable("codigoProduto") Integer id){
        return  service.getById(id);
    }

    @GetMapping
    public List<Produto> findAll(){
        return  service.findAll();
    }

    @DeleteMapping("{codigoProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable("codigoProduto") Integer id) {
             service.delete(id);
    }

    @GetMapping("/filtro")
    public List<Produto> find(Produto filtro){
        return service.find(filtro);
    }

    @PutMapping("{codigoProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("codigoProduto") Integer id,@RequestBody Produto produto){
       service.update(id,produto);
    }

}
