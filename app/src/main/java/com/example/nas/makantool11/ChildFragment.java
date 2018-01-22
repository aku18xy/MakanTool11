package com.example.nas.makantool11;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String SELECTED_OBJECT ="selected_object";
    public static final String KEY_PARCELABLE ="key_parcel";
    public static final String IMAGE_TRANSITION = "image_transition";
    public static final String HUHU = "huhu";
    private int mPage;
    RecyclerView recycle;
    MenuAdapter adapter;
    List<CategoryModel> cats;
    List<FoodModel> foods;
    int aa, bb,cc;
    float size, size1;
    FloatingActionButton fabCart, fabCall;
    LinearLayout llFab;
    TextView txtTotal, txtQuant;


    public ChildFragment() {
        // Required empty public constructor
    }

    public static ChildFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ChildFragment fragment = new ChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        cats = MainActivity.getCats();

        foods = cats.get(mPage -1).getFood();
        setRatio();
    }

    public void setRatio() {
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());

        //dapatkan width device
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        int pxHeight = outMetrics.heightPixels;
        int pxWidth = (outMetrics.widthPixels)-padding;

        // test size phone lain
        int px = 696;

        //kira ikut golden ratio
        aa = (int) (pxWidth/1.618);
        bb = (int) (aa/1.618);
        cc = (int) (bb+((aa-bb)/2));

//        size = (float) ((0.0374*pxWidth)+0.631);
//        size1 = (float) ((0.0267*pxWidth)+(-0.4064));
//        if (size1 < 5.0f){
//            size1=5.0f;
//        }
        size = (float) ((0.1167*outMetrics.widthPixels)+(-38.0000));
        size1 = (float) ((0.0667*outMetrics.widthPixels)+(-20.0000));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_child, container, false);

        recycle = v.findViewById(R.id.recycle);
        fabCart = getActivity().findViewById(R.id.fabCart);
        llFab = getActivity().findViewById(R.id.llfab);
        fabCall = getActivity().findViewById(R.id.fabCall);
        txtTotal = getActivity().findViewById(R.id.txtTtlAwl);
        txtQuant = getActivity().findViewById(R.id.txtQuant);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MenuAdapter(getActivity(), getLayoutInflater(), foods, aa, bb, cc, size, size1);
        recycle.setAdapter(adapter);
        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                if (dy > 0 && fabCart.getVisibility() == View.VISIBLE) {
//                    fabCart.hide();
//                    fabCall.hide();
//                    txtTotal.setVisibility(View.INVISIBLE);
//                    txtQuant.setVisibility(View.INVISIBLE);
//                } else if (dy < 0 && fabCart.getVisibility() != View.VISIBLE) {
//                    fabCart.show();
//                    fabCall.show();
//                    txtTotal.setVisibility(View.VISIBLE);
//                    txtQuant.setVisibility(View.VISIBLE);
//
//                }

//                if (dy > 0 && fabCall.getVisibility() == View.VISIBLE) {
//                    fabCall.hide();
//                } else if (dy < 0 && fabCall.getVisibility() != View.VISIBLE) {
//                    fabCall.show();
//                }
//
//                if (dy > 0 && txtTotal.getVisibility() == View.VISIBLE) {
//                    txtTotal.setVisibility(View.INVISIBLE);
//                } else if (dy < 0 && fabCall.getVisibility() != View.VISIBLE) {
//                    txtTotal.setVisibility(View.VISIBLE);
//                }

                    if (dy > 0 && llFab.getVisibility() == View.VISIBLE) {
                        llFab.setVisibility(View.GONE);
                        llFab.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_out_right));
                    } else if (dy < 0 && llFab.getVisibility() != View.VISIBLE) {
                        llFab.setVisibility(View.VISIBLE);
                        llFab.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_slide_in_left));
                    }
            }
        });
    }
}
