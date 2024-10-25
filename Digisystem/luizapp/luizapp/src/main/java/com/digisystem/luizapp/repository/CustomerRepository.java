package com.digisystem.luizapp.repository;

import com.digisystem.luizapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}