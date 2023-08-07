package com.example.springbootthymeleaftw.service;

import com.example.springbootthymeleaftw.model.entity.*;
import com.example.springbootthymeleaftw.repository.FirmRepository;
import com.example.springbootthymeleaftw.repository.ProductRepository;
import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.repository.ProductRepository;
import com.example.springbootthymeleaftw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final FirmRepository firmRepository;


    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }

    public void saveProduct(ProductEntity Product) {
        productRepository.save(Product);
    }

    public void createProduct(ProductEntity Product)
    {
        List <ProductEntity> list = getAllProducts();
        list.add(Product);
        saveProduct(Product);
    }

    public void updateProduct(ProductEntity Product)
    {
        ProductEntity prod = productRepository.findByProductName(Product.getProductName());
        prod.setQuantity(prod.getQuantity()-Product.getQuantity());
        saveProduct(prod);
    }

}
