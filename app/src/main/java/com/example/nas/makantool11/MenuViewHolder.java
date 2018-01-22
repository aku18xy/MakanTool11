package com.example.nas.makantool11;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

/**
 * Created by nas on 19/12/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder {

    public TextView txtNama, txtHarga, txtDesc;
    public ImageView gbrFnb;
    LinearLayout ll0, ll1, ll2;
    ElegantNumberButton btn;

    public MenuViewHolder(View itemView) {
        super(itemView);

        txtNama = itemView.findViewById(R.id.txtNama);
        txtDesc = itemView.findViewById(R.id.txtDesc);
        txtHarga = itemView.findViewById(R.id.txtHarga);
        gbrFnb = itemView.findViewById(R.id.gbrFnB);
        ll0 = itemView.findViewById(R.id.ll0);
        ll1 = itemView.findViewById(R.id.ll1);
        ll2 = itemView.findViewById(R.id.ll2);
        btn = itemView.findViewById(R.id.btnNumber);

    }
}
