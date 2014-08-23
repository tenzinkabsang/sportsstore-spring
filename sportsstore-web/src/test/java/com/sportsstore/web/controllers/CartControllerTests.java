package com.sportsstore.web.controllers;


import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Cart;
import com.sportsstore.models.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CartControllerTests {
    /* values instantiated before each tests */
    Cart cart = new Cart();
    ProductRepository repo = mock(ProductRepository.class);
    Model uiModel = new ExtendedModelMap();
    RedirectAttributes redirectAttr = new RedirectAttributesModelMap();
    Product product = new Product();

    CartController ctrl = new CartController(repo, cart);

    @Before
    public void setUpTestProduct(){
        product.setProductId(1);
        product.setName("test_name");
        product.setDescription("test_description");
        product.setPrice(BigDecimal.TEN);
        product.setCategory("test_category");
    }

    @Test
    public void addToCart_retrieves_product_from_repository_before_adding_to_cart(){
        ctrl.addToCart(redirectAttr, 1, "cart/returnUrl");
        verify(repo).getProductById(1);
    }

    @Test
    public void addToCart_adds_item_to_cart(){
        when(repo.getProductById(anyInt())).thenReturn(product);

        ctrl.addToCart(redirectAttr, 1, "cart/returnUrl");

        assertThat(cart.computeTotalValue(), is(BigDecimal.TEN));
    }

    @Test
    public void addToCart_increments_quantity_if_product_already_in_cart(){
        when(repo.getProductById(anyInt())).thenReturn(product);

        // add same product (productId: 1234) twice
        ctrl.addToCart(redirectAttr, 1, "cart/returnUrl");
        ctrl.addToCart(redirectAttr, 1, "cart/returnUrl");

        assertThat(cart.getCartLines().size(), is(1));
        assertThat(cart.computeTotalValue(), is(BigDecimal.valueOf(20)));
    }

    @Test
    public void removeFromCart_removes_all_product_for_the_given_Id(){
        when(repo.getProductById(1)).thenReturn(product);

        Product p2 = new Product();
        p2.setProductId(2);
        p2.setPrice(BigDecimal.ONE);

        // add some products to cart
        cart.addItem(product, 3);
        cart.addItem(p2, 1);

        // now remove productId: 1
        ctrl.removeFromCart(redirectAttr, 1, "cart/returnUrl");

        assertThat(cart.getCartLines().size(), is(1));
        Product productInCart = cart.getCartLines().get(0).getProduct();
        assertThat(productInCart.getProductId(), is(2));
        assertThat(cart.computeTotalValue(), is(BigDecimal.ONE));
    }
}
