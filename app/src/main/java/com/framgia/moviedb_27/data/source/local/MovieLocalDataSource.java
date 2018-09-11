package com.framgia.moviedb_27.data.source.local;

import android.content.Context;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.source.MovieDataSource;
import com.framgia.moviedb_27.data.source.local.sqlite.DatabaseController;
import java.util.List;

public class MovieLocalDataSource implements MovieDataSource.LocalDataSource {

    private DatabaseController mDatabaseController;

    public MovieLocalDataSource(Context context) {
        mDatabaseController = new DatabaseController(context);
    }

    @Override
    public void insertFavoriteMovie(Movie movie) {
        mDatabaseController.insertMovieToDatabase(movie);
    }

    @Override
    public List<Movie> getFavoriteMovie() {
        return mDatabaseController.getListMovieFromDatabase();
    }

    @Override
    public boolean isInFavoriteList(int movieId) {
        return mDatabaseController.isInFavoriteList(movieId);
    }

    @Override
    public void deleteMovieFavorite(int movieId) {
        mDatabaseController.deleteMovieFavorite(movieId);
    }
}
