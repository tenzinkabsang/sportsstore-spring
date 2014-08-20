package com.sportsstore.models;

public class PagingInfo {
    private int totalItems;
    private int itemsPerPage;
    private int currentPage;

    public PagingInfo(int currentPage, int itemsPerPage, int totalItems) {
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
    }

    public int getTotalPages(){
        return (int)Math.ceil((double)totalItems/itemsPerPage);
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getCssClass(int page){
        return page == currentPage ? "active" : "";
    }
}

