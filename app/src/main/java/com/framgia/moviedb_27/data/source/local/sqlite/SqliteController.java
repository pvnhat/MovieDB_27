package com.framgia.moviedb_27.data.source.local.sqlite;

import com.framgia.moviedb_27.data.model.Movie;
import java.util.List;

public interface SqliteController {
    void insertMovieToDatabase(Movie movie);

    List<Movie> getListMovieFromDatabase();

    boolean isInFavoriteList(int movieId);

    void deleteMovieFavorite(int movieId);
}
