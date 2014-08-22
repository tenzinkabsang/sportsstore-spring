package com.sportsstore.models;
import org.springframework.format.annotation.NumberFormat;

import static org.springframework.format.annotation.NumberFormat.Style.CURRENCY;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String name;
    private String description;

    @NumberFormat(style = CURRENCY)
    private BigDecimal price;

    private String category;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
