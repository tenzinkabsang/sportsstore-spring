package com.sportsstore.repositories;


import com.sportsstore.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
}
