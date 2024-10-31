package com.digisystem.luizapp.service;

import com.digisystem.luizapp.model.Compra;
import com.digisystem.luizapp.model.Compra;
import com.digisystem.luizapp.model.Customer;
import com.digisystem.luizapp.model.Product;
import com.digisystem.luizapp.repository.CompraRepository;
import com.digisystem.luizapp.repository.CompraRepository;
import com.digisystem.luizapp.repository.CustomerRepository;
import com.digisystem.luizapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerService customerService;


    //@Autowired
    //private CompraMapper CompraMapper;

    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    public List<Compra> getAllComprasFromCustomerName(String name)
    {
        return compraRepository.findComprasByCustomerName(name);
    }

    public List<Compra> getAllComprasFromCustomerId(Long id)
    {
        return compraRepository.findComprasByCustomerId(id);
    }



    /*public List<CompraDTO> getAllCompras() {
        return CompraMapper.toListDTO(CompraRepository.findAll());
    }*/

/*
    public CompraDTO getCompraById(Long id) {
        return CompraMapper.toDTO(CompraRepository.findById(id).orElse(null));
    }
*/

    public Compra getCompraById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }



    public boolean customerExists(Compra compra)
    {
        return customerRepository.existsById(compra.getCustomer().getCustomer_id());
    }

    public boolean productExists(Compra compra)
    {
        return productRepository.existsById(compra.getProduct().getProduct_id());
    }

    public void saveCompra(Compra compra) {

        if(productExists(compra) && customerExists(compra)) {
            compraRepository.save(compra);
            Customer updateCustomer = customerRepository.getReferenceById(compra.getCustomer().getCustomer_id());
            Product product = productRepository.getReferenceById(compra.getProduct().getProduct_id());
            if(updateCustomer.getBalance() >= product.getPrice()){
            updateCustomer.setBalance(updateCustomer.getBalance() - product.getPrice());
            customerService.update(updateCustomer.getCustomer_id(), updateCustomer);
            }

        }
    }

    public void deleteCompra(Long id) {
        compraRepository.deleteById(id);
    }

    // @Transactional
    public void update(Long id, Compra updatedCompra) {
        compraRepository
                .findById(id) // returns Optional<User>
                .ifPresent(compra-> {
                    compra.setCustomer(updatedCompra.getCustomer());;
                    compra.setProduct(updatedCompra.getProduct());;
                    compra.setQuantidade(updatedCompra.getQuantidade());


                    compraRepository.save(compra);
                });
    }
}
