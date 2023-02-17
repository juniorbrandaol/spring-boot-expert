package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Marca;
import com.eblj.curse.data.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {
    @Autowired
    private MarcaService service;

    public MarcaController(MarcaService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Marca save(@RequestBody Marca marca){
        return service.salvar(marca);
    }

    @GetMapping("{id}")
    public Marca getById( @PathVariable Integer id){
        return service.getById(id);
    }

    @GetMapping
    public List<Marca> findAll(){
        return service.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id){
        service.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,@RequestBody Marca marca){
        service.update(id,marca);
    }
}
