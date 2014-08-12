package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

public class ProductControllerTests {

    Model uiModel = new ExtendedModelMap();

    @Test
    public void list_Calls_Repository_To_Get_Products(){
        ProductRepository repo = mock(ProductRepository.class);

        ProductController ctrl = new ProductController(repo);

        ctrl.list(uiModel, 1);

        verify(repo).getAllProducts(anyInt(), anyInt());
    }
}
