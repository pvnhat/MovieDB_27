package com.framgia.moviedb_27.data.source;

import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.data.model.MorePopularActor;
import com.framgia.moviedb_27.data.model.MoreTrailer;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.model.credit.Credit;
import io.reactivex.Observable;
import java.util.List;

public interface MovieDataSource {
    interface RemoteDataSource {
        Observable<MoreMovie> getPopularMovie(int page);

        Observable<MoreMovie> getTopRatedMovie(int page);

        Observable<MoreMovie> getUpcomingMovie(int page);

        Observable<MoreMovie> getNowPlayingMovie(int page);

        Observable<MoreMovie> getMovieByGenre(int genreId, int page);

        Observable<MoreMovie> getMovieBySearching(String keyWord, int page);

        Observable<MoreTrailer> getTrailerList(int movieId);

        Observable<Credit> getCreditByMovieId(int movieId);

        Observable<MorePopularActor> getPopularPersons(int page);

        Observable<MoreMovie> getMovieByCrew(int crewId, int page);

        Observable<MoreMovie> getMovieByCast(int castId, int page);
    }

    interface LocalDataSource {
        void insertFavoriteMovie(Movie movie);

        List<Movie> getFavoriteMovie();

        boolean isInFavoriteList(int movieId);

        void deleteMovieFavorite(int movieId);
    }
}
