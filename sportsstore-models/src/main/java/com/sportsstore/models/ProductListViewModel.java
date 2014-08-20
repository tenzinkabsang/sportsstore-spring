package com.sportsstore.models;

import java.util.List;

public class ProductListViewModel {
    private List<Product> products;
    private PagingInfo pagingInfo;
    private String currentCategory;

    public ProductListViewModel(List<Product> products, String category, PagingInfo pagingInfo) {
        this.products = products;
        this.currentCategory = category;
        this.pagingInfo = pagingInfo;
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }
}
