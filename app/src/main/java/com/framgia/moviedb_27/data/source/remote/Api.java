package com.framgia.moviedb_27.data.source.remote;

import com.framgia.moviedb_27.data.model.MoreGenre;
import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.data.model.MorePopularActor;
import com.framgia.moviedb_27.data.model.MoreTrailer;
import com.framgia.moviedb_27.data.model.credit.Credit;
import com.framgia.moviedb_27.utils.Constants;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    interface GenreSource {
        @GET("genre/movie/list" + Constants.API_KEY)
        Observable<MoreGenre> getGenreList();
    }

    interface MovieSource {

        @GET("movie/popular" + Constants.API_KEY)
        Observable<MoreMovie> getPopularMovie(@Query("page") int pageNum);

        @GET("movie/now_playing" + Constants.API_KEY)
        Observable<MoreMovie> getNowPlayingMovie(@Query("page") int pageNum);

        @GET("movie/upcoming" + Constants.API_KEY)
        Observable<MoreMovie> getUpcomingMovie(@Query("page") int pageNum);

        @GET("movie/top_rated" + Constants.API_KEY)
        Observable<MoreMovie> getTopRatedMovie(@Query("page") int pageNum);

        @GET("discover/movie" + Constants.API_KEY)
        Observable<MoreMovie> getMovieByGenre(@Query("with_genres") int categoryId,
                @Query("page") int pageNum);

        @GET("search/movie" + Constants.API_KEY)
        Observable<MoreMovie> getMovieBySearching(@Query("query") String searchKey,
                @Query("page") int pageNum);

        @GET("movie/{movieId}/videos" + Constants.API_KEY)
        Observable<MoreTrailer> getTrailerList(@Path("movieId") int movieId);

        /**
         * Credit include Crew List, Cast List
         */
        @GET("movie/{movieId}/credits" + Constants.API_KEY)
        Observable<Credit> getCreditByMoveId(@Path("movieId") int movieId);

        /**
         * Get popular person and its movie
         */
        @GET("person/popular" + Constants.API_KEY)
        Observable<MorePopularActor> getPopularPersons(@Query("page") int page);

        @GET("discover/movie" + Constants.API_KEY)
        Observable<MoreMovie> getMovieByCast(@Query("with_cast") int castId,
                @Query("page") int page);

        @GET("discover/movie" + Constants.API_KEY)
        Observable<MoreMovie> getMovieByCrew(@Query("with_crew") int crewId,
                @Query("page") int page);
    }
}
