package com.example.nas.makantool11;

import java.util.List;

/**
 * Created by nas on 20/01/2018.
 */

public class CustomerModel {

    String PhoneId;
    List<Cart> YourCart;

    public CustomerModel(String phoneId, List<Cart> yourCart) {
        PhoneId = phoneId;
        this.YourCart = yourCart;
    }

    public static class Cart {
        String Name;
        String Quantity;
        String CategoryId;

        public Cart(String name, String quantity, String categoryId) {
            Name = name;
            Quantity = quantity;
            CategoryId = categoryId;
        }

        public void setName(String name) {
            Name = name;
        }

        public void setQuantity(String quantity) {
            Quantity = quantity;
        }

        public void setCategoryId(String categoryId) {
            CategoryId = categoryId;
        }
    }
}
