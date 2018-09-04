package com.framgia.moviedb_27.data.source;

import com.framgia.moviedb_27.data.model.MoreGenre;
import io.reactivex.Observable;

public interface GenreDataSource {
    interface RemoteDataSource {
        Observable<MoreGenre> getGenreListData();
    }
}
