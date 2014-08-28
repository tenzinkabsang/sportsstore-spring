package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Product;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@Test
public class AdminControllerTests {

    @Mock
    private ProductRepository repo;

    @InjectMocks
    private AdminController ctrl;

    Model uiModel;

    @BeforeTest
    public void Before(){
        ctrl = new AdminController();
        uiModel = new ExtendedModelMap();
        MockitoAnnotations.initMocks(this);
    }

    public void index_returns_all_products_from_repository(){
        when(repo.getAllProducts()).thenReturn(Arrays.asList(new Product(), new Product()));

        String viewPath = ctrl.Index(uiModel);

        List<Product> products = (List<Product>)uiModel.asMap().get("productList");

        verify(repo).getAllProducts();
        assertEquals(products.size(), 2);
        assertEquals(viewPath, "admin/index");
    }
}
