package com.example.nas.makantool11;

import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.transition.Fade;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private static List<CartModel> carts;
    RecyclerView recycle;
    private static TextView txtTotal, txtRestaurant, txtAddress;
    CartAdapter adapter;
    FnBModel fnB = MainActivity.getFnb();
    private FloatingActionButton fabBack;
    RecyclerAkuPunye recyclerAkuPunye;
    private CoordinatorLayout layoutCart;
    private LinearLayout llCart,  layoutCart2, llRecycle;
    private CardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        carts = CartController.getCarts();
        txtTotal = findViewById(R.id.txtTotal);


        loadCartList();
        headerReceipt();
        showTotal();
        back();
        placeOrder();


        layoutCart =findViewById(R.id.layoutCart);
        layoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (llCart.getVisibility() == View.VISIBLE){
//                    llCart.setVisibility(View.INVISIBLE);
//                    llCart.setAnimation(AnimationUtils.loadAnimation(CartActivity.this, R.anim.anim_slide_out_right));
//                }else if(llCart.getVisibility() == View.INVISIBLE){
//                    llCart.setVisibility(View.VISIBLE);
//                    llCart.setAnimation(AnimationUtils.loadAnimation(CartActivity.this, R.anim.anim_slide_in_left));
//                }

                hideFab();
            }
        });

    }

    private void placeOrder() {


        FloatingActionButton fabOrder = findViewById(R.id.fabOrder);
        fabOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CustomerModel.Cart> yourCart = new ArrayList<>();
                for (CartModel cart : carts){
                    yourCart.add(new CustomerModel.Cart(cart.getName(), cart.getQuantity(), cart.getMenuId()));
                }

                CustomerModel customerModel = new CustomerModel("1121213125653", yourCart);

                String json = new Gson().toJson(customerModel);
            }
        });
    }

    public void hideFab() {

        llCart = findViewById(R.id.llCart);

        if (llCart.getVisibility() == View.VISIBLE){
            llCart.setVisibility(View.INVISIBLE);
            llCart.setAnimation(AnimationUtils.loadAnimation(CartActivity.this, R.anim.anim_slide_out_right));
        }else if(llCart.getVisibility() == View.INVISIBLE){
            llCart.setVisibility(View.VISIBLE);
            llCart.setAnimation(AnimationUtils.loadAnimation(CartActivity.this, R.anim.anim_slide_in_left));
        }

    }

    private void back() {
        fabBack = findViewById(R.id.fabBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CartActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }

    private void headerReceipt() {
        txtRestaurant = findViewById(R.id.txtRestaurant);
        txtAddress = findViewById(R.id.txtAddress);
        txtRestaurant.setText(fnB.getRestaurant());
        txtAddress.setText(fnB.getAddress());
    }

    public static void showTotal() {
        double total = CartController.getTOTAL();
        txtTotal.setText("RM" + String.format("%.2f",total));
    }

    private void loadCartList() {
        card1 = findViewById(R.id.card1);
        llRecycle = findViewById(R.id.llRecycleCart);

        recyclerAkuPunye = findViewById(R.id.recycleCart);
        recyclerAkuPunye.setHasFixedSize(true);
        recyclerAkuPunye.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        adapter = new CartAdapter(CartActivity.this, getLayoutInflater(), carts);
        recyclerAkuPunye.setAdapter(adapter);

//        SwipeUtil swipeToRemove = new SwipeUtil(0, ItemTouchHelper.LEFT, CartActivity.this) {
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                super.onSwiped(viewHolder, direction);
//                Toast.makeText(CartActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
//                //Remove swiped item from list and notify the RecyclerView
//                final int position = viewHolder.getAdapterPosition();
////                carts.remove(position);
////                adapter.notifyItemRemoved(position);
////                adapter.notifyItemRangeChanged(position, CartController.getCartSize());
////                adapter.notifyDataSetChanged();
//            }
//        };
//
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToRemove);
//        itemTouchHelper.attachToRecyclerView(recyclerAkuPunye);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(CartActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(CartActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                final int position = viewHolder.getAdapterPosition();
                if (CartController.getCartSize() == 1) {
                    carts.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, CartController.getCartSize());
                    showTotal();
                }else if(CartController.getCartSize() > 1) {
                    carts.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, CartController.getCartSize());
                    showTotal();

                    float iniH = card1.getMeasuredHeight();
                    float finH = iniH - (recyclerAkuPunye.getChildAt(1).getHeight());

                    ResizeAnimation anim = new ResizeAnimation(card1, iniH, finH);
                    card1.setAnimation(anim);

                }

//                carts.remove(position);
//                adapter.notifyItemRemoved(position);
//                adapter.notifyItemRangeChanged(position, CartController.getCartSize());
//
//                showTotal();



//                float iniH = card1.getMeasuredHeight();
//                float finH = iniH - (recyclerAkuPunye.getChildAt(1).getHeight());
////
//                ResizeAnimation anim = new ResizeAnimation(card1, iniH, finH);
//                card1.setAnimation(anim);

                //
//                recyclerAkuPunye.animate();
//                card1.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_resize_up));

//                Handler handler = new Handler();
//                Runnable r = new Runnable() {
//                    @Override
//                    public void run() {
//                        ViewGroup.LayoutParams params = recyclerAkuPunye.getLayoutParams();
//                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//                        recyclerAkuPunye.setLayoutParams(params);
//                    }
//                };
//                handler.postDelayed(r, 600);

//                ViewGroup.LayoutParams params = recyclerAkuPunye.getLayoutParams();
//                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//                recyclerAkuPunye.setLayoutParams(params);

//                ValueAnimator anim = ValueAnimator.ofInt(recyclerAkuPunye.getMeasuredHeight(), -100);
//                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                        int val = (Integer) valueAnimator.getAnimatedValue();
//                        ViewGroup.LayoutParams layoutParams = recyclerAkuPunye.getLayoutParams();
//                        layoutParams.height = val;
//                        recyclerAkuPunye.setLayoutParams(layoutParams);
//                    }
//                });
//                anim.setDuration(3000);
//                anim.start();
//                recyclerAkuPunye.setLayoutTransition(new LayoutTransition());
//                LayoutTransition lt = new LayoutTransition();
//                lt.disableTransitionType(LayoutTransition.DISAPPEARING);
//                llRecycle.setLayoutTransition(lt);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerAkuPunye);

//        recycle = findViewById(R.id.recycleCart);
//        recycle.setHasFixedSize(true);
//        recycle.setLayoutManager(new LinearLayoutManager(CartActivity.this));
//        adapter = new CartAdapter(CartActivity.this, getLayoutInflater(), carts);
//        recycle.setAdapter(adapter);
//        int h = recycle.getMeasuredHeight();
//        int he = recycle.getHeight();
//        int hee = recycle.computeVerticalScrollRange();
//        int c = recycle.getChildCount();

    }

//    public class RecyclerViewUtil extends RecyclerView{
//
//
//        public RecyclerViewUtil(Context context) {
//            super(context);
//        }
//
//        @Override
//        protected void onMeasure(int widthSpec, int heightSpec) {
////            heightSpec = MeasureSpec.makeMeasureSpec(100, MeasureSpec.AT_MOST);
////
////            setMeasuredDimension(widthSpec,heightSpec);
//
//            //dapatkan width device
//            Display display = getWindowManager().getDefaultDisplay();
//            DisplayMetrics outMetrics = new DisplayMetrics ();
//            display.getMetrics(outMetrics);
//
//            int maxHeight = outMetrics.heightPixels - 200;
//            int pxWidth = (outMetrics.widthPixels);
//
//
//            if (maxHeight > 0){
//                int hSize = MeasureSpec.getSize(heightSpec);
//                int hMode = MeasureSpec.getMode(heightSpec);
//
//                switch (hMode){
//                    case MeasureSpec.AT_MOST:
//                        heightSpec = MeasureSpec.makeMeasureSpec(Math.min(hSize, maxHeight), MeasureSpec.AT_MOST);
//                        break;
//                    case MeasureSpec.UNSPECIFIED:
//                        heightSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
//                        break;
//                    case MeasureSpec.EXACTLY:
//                        heightSpec = MeasureSpec.makeMeasureSpec(Math.min(hSize, maxHeight), MeasureSpec.EXACTLY);
//                        break;
//                }
//            }
//            super.onMeasure(widthSpec, heightSpec);
//        }
//    }
}
