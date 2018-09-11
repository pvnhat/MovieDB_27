package com.framgia.moviedb_27.screen.favorite_screen;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.databinding.ActivityFavoriteBinding;

public class FavoriteActivity extends AppCompatActivity {

    private FavoriteViewModel mFavoriteViewModel;

    public static Intent newInstance(Context context) {
        return new Intent(context, FavoriteActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewModel();
    }

    private void setViewModel() {
        mFavoriteViewModel = new FavoriteViewModel(this.getApplicationContext());
        ActivityFavoriteBinding favoriteBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_favorite);
        favoriteBinding.setViewModel(mFavoriteViewModel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.text_favorite));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFavoriteViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mFavoriteViewModel.onStop();
        super.onStop();
    }
}
