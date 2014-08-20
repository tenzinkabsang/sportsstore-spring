package com.sportsstore.models;

import java.util.Collection;
import java.util.List;

public class CategoryViewModel {
    private String selectedCategory;
    private List<String> categories;

    public CategoryViewModel(String selectedCategory, List<String> categories){
        this.selectedCategory = selectedCategory;
        this.categories = categories;
    }

    public String isSelected(String category){
        return category.equals(selectedCategory) ? "active" : "";
    }

    public Collection<String> getCategories(){
        return categories;
    }
}
