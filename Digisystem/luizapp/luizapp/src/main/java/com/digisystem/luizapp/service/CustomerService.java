package com.digisystem.luizapp.service;

//import com.digisystem.luizapp.dto.CustomerDTO;
//import com.digisystem.luizapp.dto.CustomerMapper;
import com.digisystem.luizapp.model.Customer;
import com.digisystem.luizapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //@Autowired
    //private CustomerMapper customerMapper;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /*public List<CustomerDTO> getAllCustomers() {
        return CustomerMapper.toListDTO(customerRepository.findAll());
    }*/

/*
    public CustomerDTO getCustomerById(Long id) {
        return CustomerMapper.toDTO(customerRepository.findById(id).orElse(null));
    }
*/

    public Customer getCustomerById(Long id) {
       return customerRepository.findById(id).orElse(null);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

   // @Transactional
    public void update(Long id, Customer updatedCust) {
        customerRepository
                .findById(id) // returns Optional<User>
                .ifPresent(cust-> {
                    cust.setName(updatedCust.getName());;
                    cust.setEmail(updatedCust.getEmail());;
                    cust.setBalance(updatedCust.getBalance());


                    customerRepository.save(cust);
                });
    }
}
