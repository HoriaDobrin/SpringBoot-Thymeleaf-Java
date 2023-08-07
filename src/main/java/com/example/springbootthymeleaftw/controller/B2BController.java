package com.example.springbootthymeleaftw.controller;


import com.example.springbootthymeleaftw.model.entity.FirmEntity;
import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.model.entity.RoleEntity;
import com.example.springbootthymeleaftw.model.entity.UserEntity;
import com.example.springbootthymeleaftw.repository.FirmRepository;
import com.example.springbootthymeleaftw.repository.ProductRepository;
import com.example.springbootthymeleaftw.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/B2BPage")
@RequiredArgsConstructor
public class B2BController {

    @Autowired
    private ProductRepository productRepository;
    private final ProductService productService;
    @Autowired
    private FirmRepository firmRepository;

    @GetMapping()
    public String open(Model model) {
        model.addAttribute("productSpec", new ProductEntity());
        model.addAttribute("products", productService.getAllProducts());
        return "B2BPage";
    }

    @PostMapping()
    public String editProducts(@ModelAttribute("productSpec") ProductEntity productSpec, BindingResult bindingResult,
                               @ModelAttribute("products") ArrayList<ProductEntity> products) {
        if (bindingResult.hasErrors()) {
            return "B2BPage";
        }
        productRepository.save(productSpec);
        for(ProductEntity prod : products)
        {
            productRepository.save(prod);
        }

        return "redirect:/B2BPage";
    }
}
