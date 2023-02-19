package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Cartao;
import com.eblj.curse.data.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartao")
public class CartaoController {

    @Autowired
    private CartaoService service;

    public CartaoController(CartaoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cartao save( @RequestBody Cartao cartao){
       return service.save(cartao);
    }

    @GetMapping("{id}")
    public Cartao findById( @PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping
    public List<Cartao> findAll(){
        return  service.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id){
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Cartao cartao){
        service.update(id,cartao);
    }

}
