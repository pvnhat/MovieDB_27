package com.framgia.moviedb_27.data.source;

import com.framgia.moviedb_27.data.model.MoreMovie;
import io.reactivex.Observable;

public interface MovieDataSource {
    interface RemoteDataSource {
        Observable<MoreMovie> getPopularMovie(int page);

        Observable<MoreMovie> getTopRatedMovie(int page);

        Observable<MoreMovie> getUpcomingMovie(int page);

        Observable<MoreMovie> getNowPlayingMovie(int page);
    }

    interface LocalDataSource {

    }
}
