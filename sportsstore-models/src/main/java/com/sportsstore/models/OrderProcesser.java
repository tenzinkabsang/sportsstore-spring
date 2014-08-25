package com.sportsstore.models;

public interface OrderProcesser{
    void processOrder(Cart cart, ShippingDetails shippingDetails);
}
