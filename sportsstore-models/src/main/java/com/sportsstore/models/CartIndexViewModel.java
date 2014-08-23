package com.sportsstore.models;

public class CartIndexViewModel {
    private final Cart cart;
    private final String returnUrl;

    public CartIndexViewModel(final Cart cart, final String returnUrl){
        this.cart = cart;
        this.returnUrl = returnUrl;
    }

    public Cart getCart(){
        return cart;
    }

    public String getReturnUrl(){
        return returnUrl;
    }
}

class CartSummaryViewModel{
    private final Cart cart;

    public CartSummaryViewModel(Cart cart){
        this.cart = cart;
    }


}
