package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Estoque;
import com.eblj.curse.data.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueService service;

    public EstoqueController(EstoqueService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estoque save( @RequestBody Estoque estoque){
        return service.save(estoque);
    }

    @GetMapping("{id}")
    public Estoque findById( @PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping
    public List<Estoque> findAll(){
        return service.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id){
        service.delete(id);
    }
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,@RequestBody Estoque estoque){
        service.update(id,estoque);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEstoque( @PathVariable Integer id, @RequestBody Estoque estoque){
        service.updateEstoque(id,estoque);
    }

}
