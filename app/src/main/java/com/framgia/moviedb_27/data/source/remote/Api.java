package com.framgia.moviedb_27.data.source.remote;

import com.framgia.moviedb_27.data.model.MoreGenre;
import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.utils.Constants;
import io.reactivex.Observable;
import retrofit2.http.GET;
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
    }
}
