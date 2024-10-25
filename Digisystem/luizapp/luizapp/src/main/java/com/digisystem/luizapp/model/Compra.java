package com.digisystem.luiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compra_id;

    @ManyToOne
    @JoinColumn(name="customer_id")//, referencedColumnName = "id")
    @JsonProperty("customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "product_id")//,referencedColumnName = "id")
    @JsonProperty("product_id")
    private Product product;

    private int quantidade;

    public Compra(Long id, Customer customer, Product product, int quantidade) {
        this.compra_id = id;
        this.customer = customer;
        this.product = product;
        this.quantidade = quantidade;
    }

    public Compra() {
    }

    public Compra(Long id) {
        this.compra_id = id;
    }

    public Compra(Integer id)
    {
        this.compra_id = (long) id;
    }

    public Long getId() {
        return compra_id;
    }

    public void setId(Long id) {
        this.compra_id = id;
    }

    public void setId(int id) {
        this.compra_id = (long)id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
