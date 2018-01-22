package com.example.nas.makantool11;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by nas on 19/12/2017.
 */

public class CartViewHolder extends RecyclerView.ViewHolder {

    public TextView txtNamaCart, txtHargaCart, undo;
    public Button imgSudu;
    public RelativeLayout rl;
    public CardView cardCart;
    public LinearLayout swipeLayout;

    public CartViewHolder(View itemView) {
        super(itemView);

        txtNamaCart = itemView.findViewById(R.id.txtNamaCart);
        txtHargaCart = itemView.findViewById(R.id.txtHargaCart);
        imgSudu = itemView.findViewById(R.id.imgSudu);
        cardCart = itemView.findViewById(R.id.cardCart);
        swipeLayout = itemView.findViewById(R.id.swipeLayout);
        undo = itemView.findViewById(R.id.undo);

    }
}
