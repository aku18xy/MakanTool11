package com.example.nas.makantool11;

import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by nas on 19/12/2017.
 */

public class TabAdapter extends FragmentStatePagerAdapter{

    List<CategoryModel> data;
    Context context;
    ViewPager viewPager;
    TabLayout tabLayout;

    public TabAdapter(FragmentManager fm, List<CategoryModel> data, Context context, ViewPager viewPager, TabLayout tabLayout) {
        super(fm);
        this.data = data;
        this.context = context;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;
    }

    @Override
    public Fragment getItem(int position) {
        return ChildFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getName();
    }

    @Override
    public int getItemPosition(Object object) {
        CategoryModel cat = (CategoryModel) ((View) object).getTag();
        int position = data.indexOf(cat);
        if (position >= 0){
            return position;
        }else {
            return POSITION_NONE;
        }
    }

    @Override
    public Parcelable saveState() {
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }


}
