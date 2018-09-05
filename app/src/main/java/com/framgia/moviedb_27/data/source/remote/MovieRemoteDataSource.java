package com.framgia.moviedb_27.data.source.remote;

import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.data.model.MorePopularActor;
import com.framgia.moviedb_27.data.model.MoreTrailer;
import com.framgia.moviedb_27.data.model.credit.Credit;
import com.framgia.moviedb_27.data.source.MovieDataSource;
import com.framgia.moviedb_27.utils.Constants;
import com.framgia.moviedb_27.utils.RetrofitData;
import io.reactivex.Observable;

public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {

    private Api.MovieSource getData() {
        return RetrofitData.getDataFromUrl(Constants.BASE_URL).create(Api.MovieSource.class);
    }

    @Override
    public Observable<MoreMovie> getPopularMovie(int page) {
        return getData().getPopularMovie(page);
    }

    @Override
    public Observable<MoreMovie> getTopRatedMovie(int page) {
        return getData().getTopRatedMovie(page);
    }

    @Override
    public Observable<MoreMovie> getUpcomingMovie(int page) {
        return getData().getUpcomingMovie(page);
    }

    @Override
    public Observable<MoreMovie> getNowPlayingMovie(int page) {
        return getData().getNowPlayingMovie(page);
    }

    @Override
    public Observable<MoreMovie> getMovieByGenre(int genreId, int page) {
        return getData().getMovieByGenre(genreId, page);
    }

    @Override
    public Observable<MoreMovie> getMovieBySearching(String keyWord, int page) {
        return getData().getMovieBySearching(keyWord, page);
    }

    @Override
    public Observable<MoreTrailer> getTrailerList(int movieId) {
        return getData().getTrailerList(movieId);
    }

    @Override
    public Observable<Credit> getCreditByMovieId(int movieId) {
        return getData().getCreditByMoveId(movieId);
    }

    @Override
    public Observable<MorePopularActor> getPopularPersons(int page) {
        return getData().getPopularPersons(page);
    }

    @Override
    public Observable<MoreMovie> getMovieByCrew(int crewId, int page) {
        return getData().getMovieByCrew(crewId, page);
    }

    @Override
    public Observable<MoreMovie> getMovieByCast(int castId, int page) {
        return getData().getMovieByCast(castId, page);
    }
}
