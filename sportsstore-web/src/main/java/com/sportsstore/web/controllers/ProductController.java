package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class ProductController {

    private final int pageSize = 4;

    private ProductRepository productRepo;

    @Inject
    public ProductController(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    @RequestMapping(method = GET)
    public String list(Model model, @RequestParam(defaultValue = "1") int page){
        List<Product> products = productRepo.getAllProducts(page, pageSize);
        model.addAttribute(products);
        return "product/list";
    }
}
