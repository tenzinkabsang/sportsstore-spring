package com.sportsstore.web.controllers;

import com.sportsstore.data.contracts.ProductRepository;
import com.sportsstore.models.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    private final OrderProcesser orderProcesser;

    @Inject
    public CartController(ProductRepository repository, Cart cart, OrderProcesser orderProcesser){
        this.repository = repository;
        this.cart = cart;
        this.orderProcesser = orderProcesser;
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

    @RequestMapping(value = "/checkout", method = GET)
    public String checkout(Model model) {
        model.addAttribute("shippingDetails", new ShippingDetails());
        return "cart/checkout";
    }

    @RequestMapping(value = "/checkout", method = POST)
    public String processCheckout(@Valid ShippingDetails shippingDetails, BindingResult bindingResult, Model model){
        if(cart.getTotalItems() == 0)
            bindingResult.addError(new ObjectError("", "Sorry your cart is empty!"));

        if(bindingResult.hasErrors())
            return "cart/checkout";

        orderProcesser.processOrder(cart, shippingDetails);

        cart.clear();

        return "cart/completed";
    }
}
























