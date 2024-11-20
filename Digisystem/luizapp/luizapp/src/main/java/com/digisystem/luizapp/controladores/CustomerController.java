package com.digisystem.luizapp.controladores;

//import com.digisystem.luizapp.dto.CustomerDTO;
import com.digisystem.luizapp.model.Customer;
import com.digisystem.luizapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")

//@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

   /* @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }*/
   @RequestMapping(
           value = "/customers",
           produces = "application/json",
           method = {RequestMethod.GET})
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    @RequestMapping(
            value = "/customers/{id}",
            produces = "application/json",
            method = {RequestMethod.GET})
    //@GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }


    @RequestMapping(
            value = "/customers/update/{id}",
            produces = "application/json",
            method = {RequestMethod.PUT})
    //@PutMapping("/customer/update/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.update(id, customer);

    }

    @RequestMapping(
            value = "/customers",
            produces = "application/json",
            method = {RequestMethod.POST})
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }

    @RequestMapping(
            value = "/customers/delete/{id}",
            produces = "application/json",
            method = {RequestMethod.DELETE})
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
