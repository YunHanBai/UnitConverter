package com.bai.unitconverter;

import java.util.List;

public class Category {
    private String categoryName;
    private int imageId;
    private int order;
    private List<Unit> unitList;

    public Category(String name, int id, int order){
        this.order = order;
        categoryName = name;
        imageId = id;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public int getImageId(){
        return imageId;
    }

    public int getOrder() {
        return order;
    }
}
