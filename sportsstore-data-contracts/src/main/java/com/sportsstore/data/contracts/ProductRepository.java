package com.sportsstore.data.contracts;

import com.sportsstore.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts(int page, int itemsPerPage);
}
