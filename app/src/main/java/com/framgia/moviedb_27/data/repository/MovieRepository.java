package com.framgia.moviedb_27.data.repository;

import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.data.model.MorePopularActor;
import com.framgia.moviedb_27.data.model.MoreTrailer;
import com.framgia.moviedb_27.data.model.credit.Credit;
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

        public Observable<MoreMovie> getMovieByGenre(int genreId, int page) {
            return mRemoteDataSource.getMovieByGenre(genreId, page);
        }

        /**
         * Search Moview with a keyword
         */
        public Observable<MoreMovie> getMovieBySearching(String keyWord, int page) {
            return mRemoteDataSource.getMovieBySearching(keyWord, page);
        }

        /**
         * get list Trailer to getKey and pass it to YoutubePlayer
         */
        public Observable<MoreTrailer> getTrailerList(int movieId) {
            return mRemoteDataSource.getTrailerList(movieId);
        }

        /**
         * To get Cast, Crew by Movie ID
         */
        public Observable<Credit> getCreditByMovieId(int movieId) {
            return mRemoteDataSource.getCreditByMovieId(movieId);
        }

        /**
         * to get Popular Person and their Movie List
         */
        public Observable<MorePopularActor> getPopularPersons(int page) {
            return mRemoteDataSource.getPopularPersons(page);
        }

        /**
         * Get movie by cast_Id
         */
        public Observable<MoreMovie> getMovieByCast(int castId, int page) {
            return mRemoteDataSource.getMovieByCast(castId, page);
        }

        /**
         * Get movie by crew_Id
         */
        public Observable<MoreMovie> getMovieByCrew(int crewId, int page) {
            return mRemoteDataSource.getMovieByCrew(crewId, page);
        }
    }

    public static class LocalSource {

    }
}
