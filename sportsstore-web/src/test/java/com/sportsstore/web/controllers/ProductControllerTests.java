package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Cart;
import com.sportsstore.models.CartLine;
import com.sportsstore.models.Product;
import com.sportsstore.models.ProductListViewModel;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProductControllerTests {

    Model uiModel = new ExtendedModelMap();

    @Test
    public void list_Calls_Repository_To_Get_Products(){
        ProductRepository repo = mock(ProductRepository.class);

        ProductController ctrl = new ProductController(repo);

        ctrl.list(uiModel, null, 1);

        verify(repo).getProducts(anyInt(), anyString(), anyInt());
    }

    @Test
    public void generate_category_specific_product_count(){
        ProductRepository repo = mock(ProductRepository.class);
        when(repo.getProductCountFor(anyString()))
                .thenReturn(3);

        ProductController ctrl = new ProductController(repo);
        ctrl.listByCategory(uiModel, "Soccer", 1);

        ProductListViewModel viewModel = (ProductListViewModel)uiModel.asMap().get("viewModel");

        assertThat(viewModel.getPagingInfo().getTotalItems(), is(3));
    }

    @Test
    public void can_add_new_lines(){
        Product p1 = new Product();
        p1.setProductId(1);
        p1.setName("P1");

        Product p2 = new Product();
        p1.setProductId(2);
        p1.setName("P2");

        Cart cart = new Cart();

        cart.addItem(p1, 1);
        cart.addItem(p2, 1);

        List<CartLine> results = cart.getCartLines();

        assertThat(results.size(), is(2));
        assertThat(results.get(0).getProduct(), equalTo(p1));
        assertThat(results.get(1).getProduct(), equalTo(p2));
    }

    @Test
    public void can_add_quantity_for_existing_lines(){
        Product p1 = new Product();
        p1.setProductId(1);
        p1.setName("P1");

        Product p2 = new Product();
        p1.setProductId(2);
        p1.setName("P2");

        Cart cart = new Cart();
        cart.addItem(p1, 1);
        cart.addItem(p2, 1);
        cart.addItem(p1, 5);

        List<CartLine> results = cart.getCartLines();

        assertThat(results.size(), is(2));
        assertThat(results.get(0).getQuantity(), is(6));
        assertThat(results.get(1).getQuantity(), is(1));
    }

    @Test
    public void can_remove_line(){
        Product p1 = new Product();
        p1.setProductId(1);
        p1.setName("P1");

        Product p2 = new Product();
        p1.setProductId(2);
        p1.setName("P2");

        Cart cart = new Cart();
        cart.addItem(p1, 3);
        cart.addItem(p2, 1);
        cart.addItem(p2, 2);

        cart.removeLine(p2);

        List<CartLine> lines = cart.getCartLines();

        assertThat(lines.size(), is(1));
        assertThat(lines.get(0).getProduct(), equalTo(p1));
        assertThat(lines.get(0).getQuantity(), is(3));
    }

    @Test
    public void calculate_cart_total(){
        Product p1 = new Product();
        p1.setProductId(1);
        p1.setPrice(BigDecimal.valueOf(100));

        Product p2 = new Product();
        p1.setProductId(2);
        p2.setPrice(BigDecimal.TEN);

        Cart cart = new Cart();
        cart.addItem(p1, 1);
        cart.addItem(p2, 1);
        cart.addItem(p1, 3);

        BigDecimal result = cart.computeTotalValue();

        assertThat(result, is(BigDecimal.valueOf(410)));
    }

    @Test
    public void can_clear_cart_contents(){
        Product p1 = new Product();
        p1.setProductId(1);
        p1.setPrice(BigDecimal.TEN);

        Product p2 = new Product();
        p1.setProductId(2);
        p1.setPrice(BigDecimal.TEN);

        Cart cart = new Cart();
        cart.addItem(p1, 3);
        cart.addItem(p2, 1);

        cart.clear();

        assertThat(cart.getCartLines().size(), is(0));
        assertThat(cart.computeTotalValue(), is(BigDecimal.ZERO));
    }
}




















