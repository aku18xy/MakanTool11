package com.example.nas.makantool11;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by nas on 19/12/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private List<CartModel> carts;

    public CartAdapter(Context context, LayoutInflater inflater, List<CartModel> carts) {
        this.context = context;
        this.inflater = inflater;
        this.carts = carts;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = inflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new CartViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {
        final CartModel ct = carts.get(position);
        holder.txtNamaCart.setText(ct.getName());
//        holder.txtHargaCart.setText(String.format("%.2f",Double.parseDouble(ct.getPrice())*Double.parseDouble(ct.getQuantity())));
        holder.txtHargaCart.setText(ct.getSumPrice());
        holder.imgSudu.setText(ct.getQuantity());
        holder.imgSudu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Initialize a new instance of LayoutInflater service
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                // Inflate the custom layout/view
//                View customView = inflater.inflate(R.layout.popup, null);
//
//                // Get the widgets reference from XML layout
////                RelativeLayout mRelativeLayout = holder.mRelativeLayout;
//
//
//                /*
//                    public PopupWindow (View contentView, int width, int height)
//                        Create a new non focusable popup window which can display the contentView.
//                        The dimension of the window must be passed to this constructor.
//
//                        The popup does not provide any background. This should be handled by
//                        the content view.
//
//                    Parameters
//                        contentView : the popup's content
//                        width : the popup's width
//                        height : the popup's height
//                */
//                // Initialize a new instance of popup window
//                final PopupWindow mPopupWindow = new PopupWindow(
//                        customView,
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        true
//                );
//
//                // Set an elevation value for popup window
//                // Call requires API level 21
//                if (Build.VERSION.SDK_INT >= 21) {
//                    mPopupWindow.setElevation(5.0f);
//                }
//
//
//                //show quantity
////                final EditText etPopup = customView.findViewById(R.id.etPopup);
////                final String kuantiti = carts.get(position).getQuantity();
////                if(etPopup.equals("")) {
////                    etPopup.setText(kuantiti);
////                }
//
//
//
////                etPopup.setText(kuantiti, TextView.BufferType.EDITABLE);
////                etPopup.setSelection(0, etPopup.getText().length());
////                //force opening soft keyboard / hide keyboard =>
////                //https://stackoverflow.com/questions/3915230/how-to-show-keyboard-on-popupwindow
////                //http://android-codes-examples.blogspot.my/2011/11/show-or-hide-soft-keyboard-on-opening.html
////                final InputMethodManager inputMgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
////                etPopup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
////                    @Override
////                    public void onFocusChange(View v, boolean hasFocus) {
////                        if (hasFocus == true) {
////
////                            inputMgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//////                            inputMgr.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
////                        }
////                    }
////                });
////                etPopup.requestFocus();
//
////                mPopupWindow.update();
//
//                //delete menu in cart
////                Button btnDel = customView.findViewById(R.id.btnDel);
////                btnDel.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        carts.remove(position);
////                        notifyItemRemoved(position);
////                        notifyItemRangeChanged(position, carts.size());
////                        inputMgr.hideSoftInputFromWindow(v.getWindowToken(),0);
////                        mPopupWindow.dismiss();
////                        ((CartActivity)context).showTotal();
////
////                    }
////                });
//
//                // Ambil quantiti baru
//                final ElegantNumberButton numBtn = customView.findViewById(R.id.numEdit);
//                numBtn.setNumber(ct.getQuantity());
//
//                //update item qty
//                Button btnUpdate = customView.findViewById(R.id.btnUpdate);
//                btnUpdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        String kuant = etPopup.getText().toString();
//                        String num = numBtn.getNumber();
//                        ct.setQuantity(num);
//                        notifyItemChanged(position);
////                        inputMgr.hideSoftInputFromWindow(v.getWindowToken(),0);
//                        mPopupWindow.dismiss();
//                        ((CartActivity)context).showTotal();
//
//                    }
//                });
//
//
//
//
//
//                // Get a reference for the custom view close button
//                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ibClose);
//
//                // Set a click listener for the popup window close button
//                closeButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // Dismiss the popup window
////                        inputMgr.hideSoftInputFromWindow(view.getWindowToken(),0);
//                        mPopupWindow.dismiss();
//                    }
//                });
//
//                /*
//                    public void showAtLocation (View parent, int gravity, int x, int y)
//                        Display the content view in a popup window at the specified location. If the
//                        popup window cannot fit on screen, it will be clipped.
//                        Learn WindowManager.LayoutParams for more information on how gravity and the x
//                        and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
//                        to specifying Gravity.LEFT | Gravity.TOP.
//
//                    Parameters
//                        parent : a parent view to get the getWindowToken() token from
//                        gravity : the gravity which controls the placement of the popup window
//                        x : the popup's x location offset
//                        y : the popup's y location offset
//                */
//                // Finally, show the popup window at the center location of root relative layout
////                mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, -100);
//                mPopupWindow.showAsDropDown(v);


                // 180118 : Manually call popup. FAILED
//                LinearLayout llCart = holder.llCart;
//                LinearLayout llCart = v.findViewById(R.id.llCart);
//                if (llCart.getVisibility() == View.VISIBLE) {
//                    llCart.setVisibility(View.GONE);
//                    llCart.setAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_out_right));
//                } else if (llCart.getVisibility() != View.VISIBLE) {
//                    llCart.setVisibility(View.VISIBLE);
//                    llCart.setAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_in_left));
//                }

                // 180118 : Use popup menu.
//                PopupMenu pop = new PopupMenu(context, v);
//                pop.getMenuInflater().inflate(R.layout.slide_popup, pop.getMenu());

//                CartActivity.nakUpdate(position);

                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.slide_popup, null);

//                if (customView.getVisibility() == View.VISIBLE) {
//                    customView.setVisibility(View.GONE);
//                    customView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_out_right));
//                } else if (customView.getVisibility() != View.VISIBLE) {
//                    customView.setVisibility(View.VISIBLE);
//                    customView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_in_left));
//                }

//                ViewGroup.LayoutParams lp = customView.getLayoutParams();


//                customView.setX(position);
//                customView.setY(position);

                // Initialize a new instance of popup window
                final PopupWindow mPopupWindow = new PopupWindow(
                        customView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        true
                );

                // Set an elevation value for popup window
                // Call requires API level 21
                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }

                // Get widget
                FloatingActionButton fabPlus = customView.findViewById(R.id.fabPlus);
                final FloatingActionButton fabMinus = customView.findViewById(R.id.fabMinus);


                fabPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = Integer.parseInt(holder.imgSudu.getText().toString());
                        holder.imgSudu.setText(String.valueOf(num+1));
                        ct.setQuantity(holder.imgSudu.getText().toString());
                        notifyItemChanged(position);
                        MainActivity.showTotal();
                        CartActivity.showTotal();
                        if (!fabMinus.isEnabled()){
                            fabMinus.setEnabled(true);
                        }
                    }
                });

                if (holder.imgSudu.getText().toString().equals("1")){
                    fabMinus.setEnabled(false);
                }

                fabMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = Integer.parseInt(holder.imgSudu.getText().toString());
                            holder.imgSudu.setText(String.valueOf(num-1));
                        ct.setQuantity(holder.imgSudu.getText().toString());
                        notifyItemChanged(position);
                        MainActivity.showTotal();
                        CartActivity.showTotal();
                            if (holder.imgSudu.getText().toString().equals("1")){
                            fabMinus.setEnabled(false);
                        }
                    }
                });

                // Finally, show the popup window at the center location of root relative layout
//                mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                mPopupWindow.showAsDropDown(v, 0, 0);


            }
        });

        holder.cardCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (holder.llCart.getVisibility() == View.VISIBLE){
//                    holder.llCart.setVisibility(View.INVISIBLE);
//                    holder.llCart.setAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_out_right));
//                }else if(holder.llCart.getVisibility() == View.INVISIBLE){
//                    holder.llCart.setVisibility(View.VISIBLE);
//                    holder.llCart.setAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_in_left));
//                }

//                Toast.makeText(context, "bole pulak", Toast.LENGTH_LONG).show();

                ((CartActivity) context).hideFab();
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }


}
