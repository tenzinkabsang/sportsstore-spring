package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.PagingInfo;
import com.sportsstore.models.Product;
import com.sportsstore.models.ProductListViewModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class ProductController {

    private final int pageSize = 4;

    @Inject
    @Qualifier("mysql")
    private ProductRepository productRepo;

    @RequestMapping(method = GET)
    public String list(Model model, @RequestParam(required = false) String category, @RequestParam(defaultValue = "1") int page){
        return processRequest(model, category, page);
    }

    @RequestMapping(value = "/page{page}", method = GET)
    public String listPage(Model model, @PathVariable int page){
        return processRequest(model, null, page);
    }

    @RequestMapping(value = "{category}/page{page}", method = GET)
    public String listByCategory(Model model, @PathVariable String category, @PathVariable int page){
        return processRequest(model, category, page);
    }

    private String processRequest(Model model, String category, int page){
        List<Product> products = productRepo.getProducts(page, category, pageSize);
        int totalItems = productRepo.getProductCountFor(category);

        PagingInfo pagingInfo = new PagingInfo(page, pageSize, totalItems);

        ProductListViewModel viewModel = new ProductListViewModel(products, category, pagingInfo);

        model.addAttribute("viewModel", viewModel);
        return "product/list";
    }
}
























