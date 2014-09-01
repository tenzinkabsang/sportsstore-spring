package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Product;
import org.springframework.ui.Model;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "edit/{productId}", method = GET)
    public String edit(@PathVariable int productId, Model uiModel) {
        uiModel.addAttribute(repo.getProductById(productId));
        return "admin/edit";
    }

    @RequestMapping(value = "edit", method = POST)
    public String edit(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttr){
        if(bindingResult.hasErrors())
            return "admin/edit";

        repo.saveProduct(product);
        redirectAttr.addFlashAttribute("message", String.format("%s saved successfully", product.getName()));
        return "redirect:index";
    }

    @RequestMapping(value = "create", method = GET)
    public String create(Model model) {
        model.addAttribute(new Product());
        return "admin/edit";
    }

    @RequestMapping(value = "delete", method = POST)
    public String delete(@RequestParam int productId, RedirectAttributes redirectAttr){
        boolean success = repo.delete(productId);
        if(success)
            redirectAttr.addFlashAttribute("message", "Item deleted successfully");

        return "redirect:index";
    }

}
