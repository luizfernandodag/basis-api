package com.digisystem.luizapp.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer com id " + id +  "não encontrado");
    }
}
