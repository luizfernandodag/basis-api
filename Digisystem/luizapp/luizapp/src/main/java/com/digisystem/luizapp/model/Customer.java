package com.digisystem.luiz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
//@Data
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    private String name;
    private String email;

    private Double balance;

    public Customer(String id, String name, String email, double balance) {
        // Converte a string id para long, se necessário
        this.customer_id = Long.parseLong(id);
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    @OneToMany
    private List<Compra> compras; //= new ArrayList<Compra>();

    public Customer(Long id, String name, String email, Double balance, List<Compra> compras) {
        this.customer_id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.compras = compras;
    }

    public Customer() {
    }

    public Customer(Long id) {
        this.customer_id = id;
    }

    public Customer(int id)
    {
        this.customer_id = (long) id;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }


    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = (long) customer_id;
    }
}
