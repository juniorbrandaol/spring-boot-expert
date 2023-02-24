package com.eblj.curse.data.rest.controller;

import com.eblj.curse.data.domain.entities.Cliente;
import com.eblj.curse.data.service.ClienteService;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {
      @Autowired
      private ClienteService service;

      public ClienteController(ClienteService service) {
            this.service = service;
      }

      @GetMapping("{codigoCliente}")
      @ApiOperation("Obter detalhes de um cliente")
      @ApiResponses({
              @ApiResponse(code = 200, message = "Cliente encontrado"),
              @ApiResponse(code = 404, message = "Cliente não encontrado")
      })
      public Cliente getClienteById(
              @ApiParam("Id do cliente") @PathVariable("codigoCliente") Integer id){
        return service.getClienteById(id);

      }

      @PostMapping()
      @ResponseStatus(HttpStatus.CREATED)
      @ApiOperation("Salvar cliente")
      @ApiResponses({
              @ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
              @ApiResponse(code = 400, message = "Erro de validação")
      })
      public Cliente save( @RequestBody @Valid Cliente cliente){
          return service.save(cliente);
      }

      @DeleteMapping("{codigoCliente}")
      @ResponseStatus(HttpStatus.NO_CONTENT)
      //por ser do tipo optional, usamos o get para retornar o id
      public void delete(@PathVariable("codigoCliente")Integer id){
                  service.delete(id);
      }

      @PutMapping("{codigoCliente}")
      @ResponseStatus(HttpStatus.NO_CONTENT)
      public void update(@PathVariable("codigoCliente") @Valid Integer id,@RequestBody Cliente cliente){
           service.update(id,cliente);
      };


    @GetMapping()
    public List<Cliente> findAll(){
        return service.findAll();
    }

     /*
      Este método lista todos os clientes em única chamada, bem como lista clientes com
      passagem de paramentros.
      */
      @GetMapping("/filtro")
      public List<Cliente> find(Cliente filtro){
        return  service.find(filtro);
      }



}
