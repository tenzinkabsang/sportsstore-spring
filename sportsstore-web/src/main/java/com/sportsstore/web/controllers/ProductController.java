package com.sportsstore.web.controllers;

import com.sportsstore.repositories.ProductRepository;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;

import javax.inject.Inject;

@Controller
public class ProductController {

    private ProductRepository productRepo;

    @Inject
    public ProductController(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    public String list(Model model){

        model.addAttribute(productRepo.getAllProducts());

        return "list";
    }
}
