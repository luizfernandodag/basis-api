package com.digisystem.luiz.repository;

import com.digisystem.luiz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByNameAndPrice(String name, double price);

}
