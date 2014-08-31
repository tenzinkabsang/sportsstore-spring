package com.sportsstore.models;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;

import static org.springframework.format.annotation.NumberFormat.Style.CURRENCY;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
    private int productId;

    @NotEmpty
    private String name;
    private String description;

    @Min(1)
    @NumberFormat(style = CURRENCY)
    private BigDecimal price;

    @NotEmpty
    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private DateTime lastUpdated;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private DateTime createDate;

    public DateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = new DateTime(lastUpdated.getTime());
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = new DateTime(createDate.getTime());
    }

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
