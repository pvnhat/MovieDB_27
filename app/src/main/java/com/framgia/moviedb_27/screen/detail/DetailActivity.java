package com.framgia.moviedb_27.screen.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_27.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding activityDetailBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_detail);
        DetailViewModel detailViewModel =
                new DetailViewModel(this.getApplicationContext(), mMovie, new DetailActorAdapter(),
                        new DetailCrewAdapter(),
                        new MovieRepository.RemoteSource(new MovieRemoteDataSource()));
        activityDetailBinding.setViewModel(detailViewModel);
    }
}
