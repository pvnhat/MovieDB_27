package com.framgia.moviedb_27.data.source.remote;

import com.framgia.moviedb_27.data.model.MoreMovie;
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
}
