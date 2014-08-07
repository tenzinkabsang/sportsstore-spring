package com.sportsstore.web.controllers;

import com.sportsstore.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class ProductController {

    private ProductRepository productRepo;

    @Inject
    public ProductController(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    @RequestMapping(method = GET)
    public String list(Model model){
        model.addAttribute(productRepo.getAllProducts());
        return "product/list";
    }
}
