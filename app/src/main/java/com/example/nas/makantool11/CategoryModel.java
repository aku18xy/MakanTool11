package com.example.nas.makantool11;

import java.util.List;

/**
 * Created by nas on 19/12/2017.
 */

public class CategoryModel {

    private String Id;
    private String Name;
    private List<FoodModel> Food;

    public CategoryModel() {
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public List<FoodModel> getFood() {
        return Food;
    }
}
