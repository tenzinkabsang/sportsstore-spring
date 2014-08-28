package com.sportsstore.web.controllers;


import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Cart;
import com.sportsstore.models.OrderProcesser;
import com.sportsstore.models.Product;
import com.sportsstore.models.ShippingDetails;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@Test
public class CartControllerTests {
    Cart cart;
    ProductRepository repo;
    OrderProcesser orderProcesser;
    Model uiModel;
    RedirectAttributes redirectAttr;
    Product product;
    CartController ctrl;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        cart = new Cart();
        repo = mock(ProductRepository.class);
        orderProcesser = mock(OrderProcesser.class);
        uiModel = new ExtendedModelMap();
        redirectAttr = new RedirectAttributesModelMap();
        ctrl = new CartController(repo, cart, orderProcesser);

        product = new Product();
        product.setProductId(1);
        product.setName("test_name");
        product.setDescription("test_description");
        product.setPrice(BigDecimal.TEN);
        product.setCategory("test_category");
    }

    public void addToCart_retrieves_product_from_repository_before_adding_to_cart(){
        ctrl.addToCart(redirectAttr, 1, "cart/returnUrl");
        verify(repo).getProductById(1);
    }

    public void addToCart_adds_item_to_cart(){
        when(repo.getProductById(anyInt())).thenReturn(product);

        ctrl.addToCart(redirectAttr, 1, "cart/returnUrl");

        assertThat(cart.computeTotalValue(), is(BigDecimal.TEN));
    }

    public void addToCart_increments_quantity_if_product_already_in_cart(){
        when(repo.getProductById(anyInt())).thenReturn(product);

        // add same product (productId: 1234) twice
        ctrl.addToCart(redirectAttr, 1, "cart/returnUrl");
        ctrl.addToCart(redirectAttr, 1, "cart/returnUrl");

        assertThat(cart.getCartLines().size(), is(1));
        assertThat(cart.computeTotalValue(), is(BigDecimal.valueOf(20)));
    }

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

    public void checkout_displays_view_for_shippingDetails(){
        String result = ctrl.checkout(uiModel);

        Object viewModel = uiModel.asMap().get("shippingDetails");

        assertThat(viewModel, is(ShippingDetails.class));

        assertThat(result, is("cart/checkout"));
    }

    public void processCheckout_with_binding_errors_does_not_process_order(){
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ctrl.processCheckout(null, bindingResult, null);

        verify(orderProcesser, never()).processOrder(any(Cart.class), any(ShippingDetails.class));
    }

    public void processCheckout_with_empty_cart_is_not_allowed(){
        BindingResult bindingResult = mock(BindingResult.class);

        ctrl.processCheckout(new ShippingDetails(), bindingResult, uiModel);

        verify(bindingResult).addError(any(ObjectError.class));
    }

    public void processCheckout_calls_orderProcessor(){
        BindingResult bindingResult = mock(BindingResult.class);
        ShippingDetails shippingDetails = new ShippingDetails();
        cart.addItem(product, 1);

        String path = ctrl.processCheckout(shippingDetails, bindingResult, uiModel);

        verify(orderProcesser).processOrder(cart, shippingDetails);
        assertThat(cart.getTotalItems(), is(0));
        assertThat(path, is("cart/completed"));
    }


}
