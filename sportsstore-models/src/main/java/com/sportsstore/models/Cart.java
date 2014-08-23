package com.sportsstore.models;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(value = "session")
public class Cart {
    private final List<CartLine> lineCollection = new ArrayList<CartLine>();

    public void addItem(Product product, int quantity){
        CartLine line = null;
        for(CartLine c: lineCollection){
            if(c.getProduct().getProductId() == product.getProductId()){
                line = c;
                break;
            }
        }

        // create a new cart if none exists
        if(line == null)
            lineCollection.add(new CartLine(product, quantity));
        else
            line.setQuantity(line.getQuantity() + quantity);
    }

    public void removeLine(Product product){

        for(int i = 0; i < lineCollection.size(); i++){
            CartLine line = lineCollection.get(i);
            if(line.getProduct().getProductId() == product.getProductId()){
                lineCollection.remove(line);
            }
        }
    }

    public BigDecimal computeTotalValue(){
        BigDecimal total = BigDecimal.ZERO;

        for(CartLine line: lineCollection){
            BigDecimal quantity = new BigDecimal(line.getQuantity());
            total = total.add(line.getProduct().getPrice().multiply(quantity));
        }

        return total;
    }

    public void clear(){
        lineCollection.clear();
    }

    public List<CartLine> getCartLines(){
        return Collections.unmodifiableList(lineCollection);
    }

    public int getTotalItems(){
        return lineCollection.size();
    }
}

