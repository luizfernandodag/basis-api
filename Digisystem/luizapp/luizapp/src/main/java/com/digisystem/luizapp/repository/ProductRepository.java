package com.digisystem.luizapp.repository;

import com.digisystem.luizapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByNameAndPrice(String name, double price);

}
