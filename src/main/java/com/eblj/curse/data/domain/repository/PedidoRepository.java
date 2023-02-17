package com.eblj.curse.data.domain.repository;

import com.eblj.curse.data.domain.entities.Cliente;
import com.eblj.curse.data.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    List<Pedido> findByCliente(Cliente obj);
}
