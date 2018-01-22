package com.example.nas.makantool11;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nas on 19/12/2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<FoodModel> foods;
    private int aa, bb, cc;
    private float size, size1;
    private static CartController cartController = new CartController();
    private static List<CartModel> carts = CartController.getCarts();

    public MenuAdapter(Context context, LayoutInflater inflater, List<FoodModel> foods, int aa, int bb, int cc, float size, float size1) {
        this.context = context;
        this.inflater = inflater;
        this.foods = foods;
        this.aa = aa;
        this.bb = bb;
        this.cc = cc;
        this.size = size;
        this.size1 = size1;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = inflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        ViewGroup.LayoutParams params = itemview.getLayoutParams();
        params.height=cc;
        params.width=(aa+bb);
        itemview.setLayoutParams(params);
        final MenuViewHolder pc = new MenuViewHolder(itemview);
        return pc;
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, int position) {
        final FoodModel fd = foods.get(position);
        holder.txtNama.setText(fd.getName());
        holder.txtDesc.setText(fd.getDescription());
        holder.txtHarga.setText("RM" + String.format("%.2f", Double.parseDouble(fd.getPrice())));
        Picasso.with(context).load(fd.getImage()).resize(bb,bb).centerCrop().into(holder.gbrFnb);
        Log.i("huhu", fd.getImage());
//        ViewGroup.LayoutParams params1 = holder.ll2.getLayoutParams();
//        params1.height = bb;
//        params1.width = bb;
//        holder.ll2.setLayoutParams(params1);
        ViewGroup.LayoutParams params2 = holder.ll1.getLayoutParams();
        params2.width=aa;
        params2.height=bb;
        holder.ll1.setLayoutParams(params2);

//        holder.txtNama.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
//        holder.txtDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, size1);
//        holder.txtHarga.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

        //kalau item dah ade dlm cart, btn set kuantiti yg ade.
        if (cartController.contain(fd.getName())){
            for (int i=0; i<carts.size(); i++)
                if (carts.get(i).getName().equals(fd.getName())){
                    holder.btn.setNumber(carts.get(i).getQuantity());
                }

        }

        //btn tambah item dlm cart.
        holder.btn.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = holder.btn.getNumber();
                cartController.addCart(context, fd.getName(), num, fd.getPrice(), fd.getMenuId());
                MainActivity.showTotal();
//                fd.setQuantity(num);

                //kalau user tekan '-' smpai 0, item dibuang dr cart.
                if (num.equals("0")){
                    if (cartController.contain(fd.getName())){
                        for (int i=0; i<carts.size(); i++){
                            if (carts.get(i).getName().equals(fd.getName())){
                                carts.remove(i);
                                MainActivity.showTotal();
                            }
                        }
                    }
                }
            }
        });

        ViewGroup.LayoutParams params3 = holder.btn.getLayoutParams();
        params3.width=bb;
        params3.height=cc-bb;
        holder.btn.setLayoutParams(params3);


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

}
