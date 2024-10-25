package com.salsatechnology.service;

import com.salsatechnology.model.ProductOrder;
import com.salsatechnology.model.ProductType;

import java.util.List;

public interface ProductOrderServiceFilter {

    List<ProductOrder> filterOrdersByProduct(ProductType productType);

}