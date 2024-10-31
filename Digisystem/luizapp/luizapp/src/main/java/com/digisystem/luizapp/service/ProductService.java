package com.digisystem.luizapp.service;

//import com.digisystem.luizapp.dto.ProductDTO;
//import com.digisystem.luizapp.dto.ProductMapper;
import com.digisystem.luizapp.model.Product;
import com.digisystem.luizapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private ProductMapper productMapper;

   // public List<Product> getAllProducts() {
     //   return productRepository.findAll();
    //}

//    public Product getProductById(Long id) {
//        return productRepository.findById(id).orElse(null);
//    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    //@Transactional
    public void update(Long id, Product updatedProd) {
        productRepository
                .findById(id)
                .ifPresent(prod -> {
                    prod.setName(updatedProd.getName());
                    prod.setPrice(updatedProd.getPrice());


                    productRepository.save(prod);
                });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
