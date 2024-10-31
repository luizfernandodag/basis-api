package com.digisystem.luizapp.repository;

import com.digisystem.luizapp.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    @Query("SELECT c FROM Compra c WHERE c.customer.name = :customerName")
    List<Compra> findComprasByCustomerName(@Param("customerName") String customerName);

    @Query("SELECT c FROM Compra c WHERE c.customer.customer_id =:id ")
    List<Compra> findComprasByCustomerId(@Param("id") Long id);
}
