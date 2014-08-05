package com.sportsstore.repositories;

import com.sportsstore.models.Product;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.*;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> getAllProducts() {

        return asList(
                product("Football", 25),
                product("Surf board", 179),
                product("Running shoes", 95));
    }

    private Product product(String name, int price){
        Product p1 = new Product();
        p1.setName(name);
        p1.setPrice(BigDecimal.valueOf(price));
        return p1;
    }
}
