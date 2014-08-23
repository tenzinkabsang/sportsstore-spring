package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.Cart;
import com.sportsstore.models.CartIndexViewModel;
import com.sportsstore.models.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
/**
 * The controller will be instantiated for each request and injected with a cart object that is scoped to "session.
 */
@Controller
@Scope("request")
@RequestMapping("cart")
public class CartController {

    private final Cart cart;

    private final ProductRepository repository;

    @Inject
    public CartController(ProductRepository repository, Cart cart){
        this.repository = repository;
        this.cart = cart;
    }

    @RequestMapping(value = "index", method = GET)
    public String index(Model model, @RequestParam String returnUrl){
        CartIndexViewModel cartModel = new CartIndexViewModel(cart, returnUrl);
        model.addAttribute("cartModel", cartModel);
        return "cart/index";
    }

    @RequestMapping(value = "addToCart", method = POST)
    public String addToCart(RedirectAttributes redirectAttr, @RequestParam int productId, @RequestParam String returnUrl){
        Product product = repository.getProductById(productId);

        if(product != null){
            cart.addItem(product, 1);
        }

        redirectAttr.addAttribute("returnUrl", returnUrl)
                    .addFlashAttribute("message", "Item added to cart!");

        return "redirect:index";
    }


    @RequestMapping(value = "removeFromCart", method = POST)
    public String removeFromCart(RedirectAttributes redirectAttr, @RequestParam int productId, @RequestParam String returnUrl){
        Product product = repository.getProductById(productId);

        if(product != null){
            cart.removeLine(product);
        }
        redirectAttr.addAttribute("returnUrl", returnUrl)
                .addFlashAttribute("message", "Item removed from cart");

        return "redirect:index";
    }
}
