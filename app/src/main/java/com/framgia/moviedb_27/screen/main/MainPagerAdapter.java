package com.framgia.moviedb_27.screen.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.databinding.SlideImageItemBinding;
import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends PagerAdapter {

    private List<String> mImages;
    private Context mContext;

    public MainPagerAdapter(Context context) {
        mContext = context;
        mImages = new ArrayList<>();
        mImages.add(mContext.getString(R.string.link1));
        mImages.add(mContext.getString(R.string.link2));
        mImages.add(mContext.getString(R.string.link3));
        mImages.add(mContext.getString(R.string.link4));
        mImages.add(mContext.getString(R.string.link5));
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        PagerAdapterViewModel pagerAdapterViewModel = new PagerAdapterViewModel();
        pagerAdapterViewModel.setData(mImages.get(position));
        SlideImageItemBinding slideImageItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                        R.layout.slide_image_item, container, false);
        container.addView(slideImageItemBinding.getRoot());
        slideImageItemBinding.setViewModel(pagerAdapterViewModel);
        slideImageItemBinding.executePendingBindings();
        return slideImageItemBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
