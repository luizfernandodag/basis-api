package com.digisystem.luizapp.controladores;

import com.digisystem.luizapp.model.Compra;
import com.digisystem.luizapp.model.Customer;
import com.digisystem.luizapp.service.CompraService;
import com.digisystem.luizapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class CompraController {


    @Autowired
    private CompraService compraService;

    /* @GetMapping
     public List<CustomerDTO> getAllCustomers() {
         return customerService.getAllCustomers();
     }*/
    @RequestMapping(
            value = "/compras",
            produces = "application/json",
            method = {RequestMethod.GET})
    public List<Compra> getAllCustomers() {
        return compraService.getAllCompras();
    }
    @RequestMapping(
            value = "/compras/{id}",
            produces = "application/json",
            method = {RequestMethod.GET})
   // @GetMapping("/customers/{id}")
    public Compra getCustomerById(@PathVariable Long id) {
        return compraService.getCompraById(id);
    }
    @RequestMapping(
            value = "/compras/pornome/{name}",
            produces = "application/json",
            method = {RequestMethod.GET})
    // @GetMapping("/customers/{id}")
    public List<Compra> getComprasByCustomerName(@PathVariable String name) {

        return compraService.getAllComprasFromCustomerName(name);
    }

    @RequestMapping(
            value = "/compras/pornome/total/{name}",
            produces = "application/json",
            method = {RequestMethod.GET})
    public double getTotalComprasByCustomerName(@PathVariable String name) {
        return compraService.getAllComprasFromCustomerName(name)
                .stream().mapToDouble(c -> c.getProduct().getPrice()).sum();

    }

    @RequestMapping(
            value = "/compras/porid/total/{id}",
            produces = "application/json",
            method = {RequestMethod.GET})
    public double getTotalComprasByCustomerId(@PathVariable Long id) {
        return compraService.getAllComprasFromCustomerId(id)
                .stream().mapToDouble(c -> c.getProduct().getPrice()).sum();

    }

    @RequestMapping(
            value = "/compras/update/{id}",
            produces = "application/json",
            method = {RequestMethod.PUT})
    //@PutMapping("/customer/update/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Compra compra) {
        compraService.update(id, compra);

    }

    @RequestMapping(
            value = "/compras",
            produces = "application/json",
            method = {RequestMethod.POST})
    public void saveCompra(@RequestBody Compra compra) {
        compraService.saveCompra(compra);
    }

    @RequestMapping(
            value = "/compras/delete/{id}",
            produces = "application/json",
            method = {RequestMethod.DELETE})
    public void deleteCustomer(@PathVariable Long id) {
        compraService.deleteCompra(id);
    }
}
