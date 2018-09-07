package com.framgia.moviedb_27.utils;

import android.databinding.BindingAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class BindingUtils {
    private BindingUtils() {
    }

    @BindingAdapter({ "bind:imageUrl" })
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    @BindingAdapter({ "bind:recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("bind:onPageChangeListener")
    public static void setOnPageChangeListener(ViewPager viewPager,
            ViewPager.OnPageChangeListener listener) {
        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(listener);
    }

    /**
     * setAdapter For RecyclerView
     */
    @BindingAdapter({ "bind:pagerAdapter" })
    public static void setAdapterForRecyclerView(ViewPager viewPager, PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }
}
