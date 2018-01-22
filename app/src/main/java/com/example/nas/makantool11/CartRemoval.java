package com.example.nas.makantool11;

/**
 * Created by nas on 19/12/2017.
 */

public class CartRemoval {

    String Name;
    String Price;
    String Quantity;
    String SumPrice;
    String Custom;
    private String MenuId;

    public CartRemoval() {
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getSumPrice() {
        SumPrice = String.format("%.2f", Double.parseDouble(Price) * Double.parseDouble(Quantity));
        return SumPrice;
    }

    public String getCustom() {
        return Custom;
    }

    public void setCustom(String custom) {
        Custom = custom;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
