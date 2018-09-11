package com.framgia.moviedb_27.utils;

import android.databinding.BindingAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.framgia.moviedb_27.R;

public class BindingUtils {
    private BindingUtils() {
    }

    @BindingAdapter({ "bind:imageUrl" })
    public static void loadImage(ImageView view, String imageUrl) {
        if (imageUrl.equals(Constants.BASE_IMAGE_LINK_NULL)) {
            view.setImageResource(R.drawable.ic_avatar);
        } else {
            Glide.with(view.getContext()).load(imageUrl).into(view);
        }
    }

    @BindingAdapter({ "bind:recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("bind:visibilityProgessbar")
    public static void setVisibility(ProgressBar progressBar, boolean value) {
        progressBar.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("bind:visibility")
    public static void setVisibility(ImageButton imageButton, boolean value) {
        imageButton.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("bind:resouce")
    public static void setResouces(ImageButton imageButton, boolean value) {
        imageButton.setImageResource(value ? R.drawable.ic_heart_active : R.drawable.ic_heart);
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
