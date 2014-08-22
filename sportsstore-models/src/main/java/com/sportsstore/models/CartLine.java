package com.sportsstore.models;

import java.math.BigDecimal;

public class CartLine{
    private final Product product;
    private int quantity;
    private BigDecimal lineTotal;

    public CartLine(final Product product, final int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public BigDecimal getLineTotal(){
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
