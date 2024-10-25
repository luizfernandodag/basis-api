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
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String name;
    private Double price;

    @OneToMany//(mappedBy = "product")
    private List<Compra> compras; //= new ArrayList<>();

    public Product(Long id, String name, double price, List<Compra> comprass) {
        this.product_id = id;
        this.name = name;
        this.price = price;
        this.compras = comprass;
    }


    public Product(String id, String name, double price, List<Compra> comprass) {
        this.product_id = Long.parseLong(id);
        this.name = name;
        this.price = price;
        this.compras = comprass;
    }




    public Product() {
    }

    public Product(Long id) {
        this.product_id = id;
    }

    public Product(int id) {
        this.product_id = (long) id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Compra> getCompras() {
        return this.compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = (long)product_id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}