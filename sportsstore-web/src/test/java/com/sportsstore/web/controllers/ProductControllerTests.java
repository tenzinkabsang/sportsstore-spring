package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Cart;
import com.sportsstore.models.CartLine;
import com.sportsstore.models.Product;
import com.sportsstore.models.ProductListViewModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

@Test
public class ProductControllerTests {
    @Mock
    private ProductRepository repo;
    @InjectMocks
    private ProductController ctrl = new ProductController();

    Model uiModel;

    @BeforeMethod(alwaysRun = true)
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
        uiModel = new ExtendedModelMap();
    }

    public void list_Calls_Repository_To_Get_Products(){
        ctrl.list(uiModel, null, 1);

        verify(repo).getProducts(anyInt(), anyString(), anyInt());
    }

    public void generate_category_specific_product_count(){
        when(repo.getProductCountFor(anyString())).thenReturn(3);

        ctrl.listByCategory(uiModel, "Soccer", 1);

        ProductListViewModel viewModel = (ProductListViewModel)uiModel.asMap().get("viewModel");

        assertThat(viewModel.getPagingInfo().getTotalItems(), is(3));
    }

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




















