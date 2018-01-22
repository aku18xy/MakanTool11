package com.example.nas.makantool11;

import java.util.List;

/**
 * Created by nas on 19/12/2017.
 */

public class FnBModel {

    private String Restaurant;
    private String Address;
    private List<CategoryModel> Category;

    public FnBModel() {
    }

    public String getRestaurant() {
        return Restaurant;
    }

    public String getAddress() {
        return Address;
    }

    public List<CategoryModel> getCategory() {
        return Category;
    }
}
