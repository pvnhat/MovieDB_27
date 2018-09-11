package com.framgia.moviedb_27.screen.genre;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.repository.GenreRepository;
import com.framgia.moviedb_27.databinding.ActivityGenreBinding;

public class GenreActivity extends AppCompatActivity {

    public static Intent getInstance(Context context) {
        Intent intent = new Intent(context, GenreActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGenreBinding activityGenreBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_genre);
        GenreViewModel genreViewModel =
                new GenreViewModel(this.getApplicationContext(), new GenreRepository(),
                        new GenreAdapter());
        activityGenreBinding.setViewModel(genreViewModel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
