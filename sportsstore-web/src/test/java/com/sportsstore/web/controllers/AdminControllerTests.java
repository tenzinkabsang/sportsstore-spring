package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Product;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
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

    public void edit_get_returns_the_product_for_the_given_id(){
        Product p = new Product();
        p.setProductId(2);
        p.setName("Bicycle");
        when(repo.getProductById(anyInt())).thenReturn(p);

        String viewPath = ctrl.edit(2, uiModel);

        Product result = (Product)uiModel.asMap().get("product");

        assertEquals(result.getProductId(), 2);
        assertEquals(result.getName(), "Bicycle");
        assertEquals(viewPath, "admin/edit");
    }

    public void edit_post_binding_errors_redirects_back_to_the_page(){
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        String path = ctrl.edit(new Product(), bindingResult, null);
        assertEquals(path, "admin/edit");
    }

    public void edit_post_valid_data_updates_product(){
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        String path = ctrl.edit(new Product(), bindingResult, new RedirectAttributesModelMap());

        verify(repo).saveProduct(any(Product.class));
        assertEquals(path, "redirect:index");
    }
}



















