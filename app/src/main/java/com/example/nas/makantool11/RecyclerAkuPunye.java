package com.example.nas.makantool11;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by nas on 19/01/2018.
 */

public class RecyclerAkuPunye extends RecyclerView {

    public RecyclerAkuPunye(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {

        heightSpec = MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);

        setMeasuredDimension(widthSpec,heightSpec);

        super.onMeasure(widthSpec, heightSpec);
    }
}
