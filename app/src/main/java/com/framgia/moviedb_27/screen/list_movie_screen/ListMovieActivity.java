package com.framgia.moviedb_27.screen.list_movie_screen;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.databinding.ActivityListMovieBinding;
import com.framgia.moviedb_27.screen.main.MainViewModel;

public class ListMovieActivity extends AppCompatActivity {

    private static final String EXTRA_KEY = "EXTRA_KEY";
    private static final String EXTRA_VALUE = "EXTRA_VALUE";

    private ListMovieViewModel mListMovieViewModel;

    public static Intent newInstance(Context context, String key, String value) {
        Intent intent = new Intent(context, ListMovieActivity.class);
        intent.putExtra(EXTRA_KEY, key);
        intent.putExtra(EXTRA_VALUE, value);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter();
        mListMovieViewModel =
                new ListMovieViewModel(this.getApplicationContext(), listMovieAdapter);

        Intent intent = getIntent();
        String key = intent.getStringExtra(EXTRA_KEY);
        String value = intent.getStringExtra(EXTRA_VALUE);
        mListMovieViewModel.setQueryData(key,value);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(value);

        ActivityListMovieBinding movieBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_list_movie);
        movieBinding.setVewModel(mListMovieViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mListMovieViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mListMovieViewModel.onStop();
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
