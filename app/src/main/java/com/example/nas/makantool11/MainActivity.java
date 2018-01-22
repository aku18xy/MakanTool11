package com.example.nas.makantool11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static android.support.v4.app.Fragment.instantiate;
import static com.example.nas.makantool11.ChildFragment.HUHU;

public class MainActivity extends AppCompatActivity {

    private static FnBModel fnb;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter adapter;
    public int aa, bb;
    private static TextView txtTtlAwl, txtQuant;
    List<CartModel> carts;
    private static Handler mHandler = new Handler();
    private CoordinatorLayout layoutMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carts = CartController.getCarts();

        txtTtlAwl = findViewById(R.id.txtTtlAwl);
        txtQuant = findViewById(R.id.txtQuant);



        dapatJson();
        loadTabs();
        fab();
        showTotal();
    }

    public static void showTotal() {

        double total = CartController.getTOTAL();
        txtTtlAwl.setText("RM" + String.format("%.2f", total));
        int quant = CartController.getCartSize();
        txtQuant.setText(String.valueOf(quant));

        //CANNOT USE RUNNABLE (UPDATE EVERY SECOND). ITS CONSUME THREAD TO MUCH.
//        Runnable runnable = new Runnable() {
//            @Override
//
//            public void run() {
//                {
////                    if (CartController.getCartSize()>0){
////                        mHandler.postDelayed(this, 3000);
////                        double total = CartController.getTOTAL();
////                        txtTtlAwl = findViewById(R.id.txtTtlAwl);
////                        txtTtlAwl.setText(String.format("%.2f", total));
////                    }
//
//                    mHandler.postDelayed(this, 3000);
//                    double total = CartController.getTOTAL();
//                    txtTtlAwl.setText("RM" + String.format("%.2f", total));
//                    int quant = CartController.getCartSize();
//                    txtQuant.setText(String.valueOf(quant));
//                }
//            }
//        };
//        mHandler.postDelayed(runnable, 0000);
    }

    private void fab() {
        layoutMain = findViewById(R.id.layoutMain);
        FloatingActionButton fab = findViewById(R.id.fabCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                Transition fadeTrans = new Fade();
//                Scene scene = Scene.getSceneForLayout(layoutMain, R.layout.activity_main, getApplicationContext());
//                TransitionManager.beginDelayedTransition(layoutMain, fadeTrans);
//                TransitionManager.go(scene,fadeTrans);
            }
        });

    }


    private void loadTabs() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
        adapter = new TabAdapter(getSupportFragmentManager(), getCats(), getApplicationContext(),viewPager,tabLayout);
        viewPager.setAdapter(adapter);
//        tabLayout.getTabAt(position);
        tabLayout.setupWithViewPager(viewPager);
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE); // 0 - for private mode
//        final SharedPreferences.Editor editor = pref.edit();
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tabPosition = position;
//                editor.putInt("tabs", tabPosition);
//                editor.commit();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    public static List<CategoryModel> getCats(){
        final List<CategoryModel> cats = fnb.getCategory();
        return cats;
    }

    private void dapatJson() {
        fnb = new Gson().fromJson(loadJSONFromAsset(), FnBModel.class);
    }

    public static FnBModel getFnb(){
        return fnb;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().
                    open("eat_it_database.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Log.d("json", "dpt baca");
        return json;
    }
}
