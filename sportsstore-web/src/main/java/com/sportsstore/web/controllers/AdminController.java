package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import org.springframework.ui.Model;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Inject
    private ProductRepository repo;

    @RequestMapping(value = "index", method = GET)
    public String Index(Model model) {
        model.addAttribute(repo.getAllProducts());
        return "admin/index";
    }
}
