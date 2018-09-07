package com.framgia.moviedb_27.screen.main;

import android.databinding.ObservableField;

public class PagerAdapterViewModel {
    public ObservableField<String> mStringObservableField = new ObservableField<>();

    public String getImageUrl() {
        return mStringObservableField.get();
    }

    public void setData(String imageLink) {
        mStringObservableField.set(imageLink);
    }
}
