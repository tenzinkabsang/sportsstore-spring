package com.sportsstore.models;

import org.springframework.stereotype.Service;

@Service
public class OrderProcesserImpl implements OrderProcesser {
    @Override
    public void processOrder(Cart cart, ShippingDetails shippingDetails) {
        System.out.println("-- process order here --");
    }
}
