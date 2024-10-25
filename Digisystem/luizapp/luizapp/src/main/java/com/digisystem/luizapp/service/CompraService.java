package com.digisystem.luiz.service;

import com.digisystem.luiz.model.Compra;
import com.digisystem.luiz.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository CompraRepository;


    public List<Compra> getAllCompras() {
        return CompraRepository.findAll();
    }

    public Compra getCompraById(Long id) {
        return CompraRepository.findById(id).orElse(null);
    }

    public void saveCompra(Compra Compra) {
        CompraRepository.save(Compra);
    }

    public void deleteCompra(Long id) {
        CompraRepository.deleteById(id);
    }

    //@Transactional
    public void update(Long id, Compra updatedCompra) {
        CompraRepository
                .findById(id) // returns Optional<User>
                .ifPresent(compra-> {
                    compra.setProduct(updatedCompra.getProduct());;
                    compra.setCustomer(updatedCompra.getCustomer());
                    compra.setQuantidade(updatedCompra.getQuantidade());


                    CompraRepository.save(compra);
                });
    }
}
