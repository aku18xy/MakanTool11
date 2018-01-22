package com.example.nas.makantool11;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nas on 20/12/2017.
 */

public class CartController {

    private static List<CartModel> carts;
    public boolean contain;
    public static double TOTAL;
    private String nama, kuantiti, harga, menuId;

    public CartController() {
    }

    public static List<CartModel> getCarts(){
        if (carts == null){
            carts = new ArrayList<>();
        }
        return carts;
    }

    public static double getTOTAL() {

        TOTAL = 0;
        for(int i=0; i<carts.size(); i++) {
            TOTAL += Double.parseDouble(carts.get(i).getSumPrice());
        }
        return TOTAL;
    }

    public static int getCartSize(){
        return getCarts().size();
    }

    public void addCart(Context context, String nama, String kuantiti, String harga, String menuId){

        if (contain(nama)){
            for (int i=0; i<getCartSize(); i++){
                CartModel cartModel = getCarts().get(i);
                if (cartModel.getName().equals(nama)){
                    cartModel.setQuantity(kuantiti);
                    getCarts().set(i, cartModel);
                }
            }

        }else {
            CartModel cart = new CartModel();
            cart.setName(nama);
            cart.setQuantity(kuantiti);
            cart.setPrice(harga);
            cart.setMenuId(menuId);
            getCarts().add(cart);
        }

//        for (CartModel cart : getCarts()) {
//            if (cart.getName().equals(nama)) {
//                Log.d("array", "checked");
//                cart.setQuantity(kuantiti);
//                getCarts().set(getCarts().indexOf(cart), cart);
//
//            } else {
//                cart = new CartModel();
//                cart.setName(nama);
//                cart.setQuantity(kuantiti);
//                cart.setPrice(harga);
//                getCarts().add(cart);
//            }
//        }


//       for (int i=0; i<getCartSize(); i++) {
//
//
//
//           if (getCarts().get(i).getName().equals(nama)) {
//               getCarts().get(i).setPrice(harga);
//
//           } else {
//               CartModel cart = new CartModel();
//               cart.setName(nama);
//               cart.setQuantity(kuantiti);
//               cart.setPrice(harga);
//               getCarts().add(cart);
//           }
//       }
    }

    public boolean contain(String nama){
        for (CartModel cart : getCarts())
            if(cart.getName().equals(nama)){
                Log.d("array", "checked");
                return true;
            }

        return false;
    }
}
