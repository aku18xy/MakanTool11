package com.example.nas.makantool11;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by nas on 19/01/2018.
 */

public class ResizeAnimation extends Animation {

    private View mView;
    private float mToHeight;
    private float mFromHeight;

    public ResizeAnimation(View v, float fromHeight, float toHeight) {
        mToHeight = toHeight;
        mFromHeight = fromHeight;
        mView = v;
        setDuration(300);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float height =
                (mToHeight - mFromHeight) * interpolatedTime + mFromHeight;
        ViewGroup.LayoutParams p = mView.getLayoutParams();
        p.height = (int) height;
        mView.requestLayout();
    }

}
