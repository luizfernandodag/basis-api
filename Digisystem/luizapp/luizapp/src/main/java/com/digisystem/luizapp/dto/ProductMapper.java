package com.digisystem.luiz.dto;

import com.digisystem.luiz.model.Product;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ProductDTO toDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public static Product toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
    public static List<ProductDTO> toListDTO(List<Product> products) {

        return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }
}
