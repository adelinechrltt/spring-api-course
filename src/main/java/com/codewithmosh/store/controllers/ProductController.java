package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.logging.Filter;

@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping("/products")
    public Iterable<ProductDto> getAllProducts(
            @RequestParam(required = false, defaultValue = "") Byte categoryId
    ){

        List<Product> products;
        if(categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAll();
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();

    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Long id){
        return productMapper.toDto(productRepository.findById(id).get());
    }
}

