package com.framgia.moviedb_27.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_27.databinding.ActivityDetailBinding;
import com.framgia.moviedb_27.screen.list_movie_screen.ListMovieActivity;
import com.framgia.moviedb_27.utils.Constants;

public class DetailActivity extends AppCompatActivity {

    private Movie mMovie;

    public static Intent newInstance(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.MOVIE_VALUE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding activityDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_detail);
        DetailViewModel detailViewModel =
                new DetailViewModel(this.getApplicationContext(), (Movie) getIntent().getExtras().getParcelable(Constants.MOVIE_VALUE), new DetailActorAdapter(),
                        new DetailCrewAdapter(),
                        new MovieRepository.RemoteSource(new MovieRemoteDataSource()));
        activityDetailBinding.setViewModel(detailViewModel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.text_movie_detail));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
