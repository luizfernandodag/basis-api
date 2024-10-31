package com.digisystem.luizapp.controladores;

//import com.digisystem.luizapp.dto.ProductDTO;
import com.digisystem.luizapp.model.Product;
import com.digisystem.luizapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")


public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(
            value = "/products",
            produces = "application/json",
            method = {RequestMethod.GET})
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(
            value = "/products/{id}",
            produces = "application/json",
            method = {RequestMethod.GET})
    //@GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    @RequestMapping(
            value = "/products",
            produces = "application/json",
            method = {RequestMethod.POST})
    public void saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }
    @RequestMapping(
            value = "/products/delete/{id}",
            produces = "application/json",
            method = {RequestMethod.DELETE})
    //@DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(
            value = "/products/update/{id}",
            produces = "application/json",
            method = {RequestMethod.PUT})
   // @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.update(id, product);

    }
}
