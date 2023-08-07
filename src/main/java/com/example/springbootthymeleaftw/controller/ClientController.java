package com.example.springbootthymeleaftw.controller;


import com.example.springbootthymeleaftw.model.entity.ProductEntity;
import com.example.springbootthymeleaftw.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/clientPage")
@RequiredArgsConstructor
public class ClientController {

    private final ProductService productService;

    @GetMapping()
    public String open(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "clientPage";
    }

    @PostMapping()
    public String buyProducts(@ModelAttribute("products") ArrayList<ProductEntity> products, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "clientPage";
        }


        /*for(ProductEntity prod : products)
        {
            productRepository.save(prod);
        }*/

        return "redirect:/clientPage";
    }
}
