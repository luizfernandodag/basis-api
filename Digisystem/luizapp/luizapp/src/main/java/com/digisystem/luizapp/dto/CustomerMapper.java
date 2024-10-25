package com.digisystem.luiz.dto;

import com.digisystem.luiz.model.Customer;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;



public class CustomerMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static CustomerDTO toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public static List<CustomerDTO> toListDTO(List<Customer> customers) {

        return customers.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }
}