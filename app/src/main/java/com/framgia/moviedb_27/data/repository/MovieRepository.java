package com.framgia.moviedb_27.data.repository;

import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.data.source.MovieDataSource;
import com.framgia.moviedb_27.data.source.remote.MovieRemoteDataSource;
import io.reactivex.Observable;

public class MovieRepository {

    public static class RemoteSource {
        private MovieDataSource.RemoteDataSource mRemoteDataSource;

        public RemoteSource() {
            mRemoteDataSource = new MovieRemoteDataSource();
        }

        public Observable<MoreMovie> getPopularMovie(int page) {
            return mRemoteDataSource.getPopularMovie(page);
        }

        public Observable<MoreMovie> getUpcomingMovie(int page) {
            return mRemoteDataSource.getUpcomingMovie(page);
        }

        public Observable<MoreMovie> getTopRatedMovie(int page) {
            return mRemoteDataSource.getTopRatedMovie(page);
        }

        public Observable<MoreMovie> getNowPlayingMovie(int page) {
            return mRemoteDataSource.getNowPlayingMovie(page);
        }
    }

    public static class LocalSource {

    }
}
