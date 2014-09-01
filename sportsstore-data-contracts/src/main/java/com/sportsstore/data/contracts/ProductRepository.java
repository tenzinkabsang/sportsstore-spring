package com.sportsstore.data.contracts;

import com.sportsstore.models.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository {
    List<Product> getProducts(int page, String category, int itemsPerPage);

    int getProductCountFor(String category);

    List<String> getAllCategories();

    Product getProductById(int productId);

    List<Product> getAllProducts();

    boolean saveProduct(Product product);

    boolean delete(int productId);
}
