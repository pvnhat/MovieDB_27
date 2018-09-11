package com.framgia.moviedb_27.screen.favorite_screen;

import android.content.Context;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.data.source.local.MovieLocalDataSource;
import com.framgia.moviedb_27.screen.BaseViewModel;
import com.framgia.moviedb_27.screen.main.MovieAdapter;
import com.framgia.moviedb_27.screen.main.OnClickListener;

public class FavoriteViewModel extends BaseViewModel
        implements OnClickListener.OnItemClickListener {
    private MovieAdapter mMovieAdapter;
    private MovieRepository.LocalSource mRepositoryLocalDataSource;

    FavoriteViewModel(Context context) {
        mMovieAdapter = new MovieAdapter();
        mRepositoryLocalDataSource =
                new MovieRepository.LocalSource(new MovieLocalDataSource(context));
        mMovieAdapter.setOnItemClick(this);
    }

    private void onLoadData() {
        mMovieAdapter.updateMovie(mRepositoryLocalDataSource.getListMovieFromDatabase());
    }

    public MovieAdapter getFavoriteAdapter() {
        return mMovieAdapter;
    }

    @Override
    protected void onStart() {
        onLoadData();
    }

    @Override
    protected void onStop() {
    }

    @Override
    public void onItemClick(Object movie) {
        // do something here
    }
}
