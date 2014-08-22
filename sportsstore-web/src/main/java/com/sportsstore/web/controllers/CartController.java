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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("cart")
@SessionAttributes("mycart")
public class CartController {

    @Inject
    private Cart cart;

    private final ProductRepository repository;

    @Inject
    public CartController(ProductRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value = "index", method = GET)
    public String index(HttpServletRequest request, Model model, @RequestParam String returnUrl){
        CartIndexViewModel cartModel = new CartIndexViewModel(getCart(request), returnUrl);
        model.addAttribute("cartModel", cartModel);
        return "cart/index";
    }

    @RequestMapping(value = "addToCart", method = POST)
    public String addToCart(HttpServletRequest request, RedirectAttributes redirectAttr, @RequestParam int productId, @RequestParam String returnUrl){
        Product product = repository.getProductById(productId);

        if(product != null){
            getCart(request).addItem(product, 1);
        }

        redirectAttr.addAttribute("returnUrl", returnUrl)
                    .addFlashAttribute("message", "Item added to cart!");

        return "redirect:index/{returnUrl}";
    }


    @RequestMapping(value = "removeFromCart", method = POST)
    public String removeFromCart(HttpServletRequest request, @RequestParam int productId, @RequestParam String returnUrl){
        Product product = repository.getProductById(productId);

        if(product != null){
            getCart(request).removeLine(product);
        }

        return "redirect:" + returnUrl;
    }


    private Cart getCart(HttpServletRequest request){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        return cart;
    }
}
