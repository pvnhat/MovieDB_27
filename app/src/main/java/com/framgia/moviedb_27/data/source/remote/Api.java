package com.framgia.moviedb_27.data.source.remote;

import com.framgia.moviedb_27.data.model.MoreGenre;
import com.framgia.moviedb_27.utils.Constants;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    interface GenreSource {
        @GET("genre/movie/list" + Constants.API_KEY)
        Observable<MoreGenre> getGenreList();
    }

    interface MovieSource {
    }
}
