package com.salsatechnology.service;

import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.model.ProductType;
import com.salsatechnology.repository.ProductOrderRepository;
import com.salsatechnology.service.ProductOrderServiceFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderServiceFilterImpl implements ProductOrderServiceFilter {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Override
    public List<ProductOrder> filterOrdersByProduct(ProductType productType) {
        return productOrderRepository.findByProductType(productType);
    }

}