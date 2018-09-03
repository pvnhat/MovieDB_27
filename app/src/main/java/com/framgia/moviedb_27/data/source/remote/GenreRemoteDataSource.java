package com.framgia.moviedb_27.data.source.remote;

import com.framgia.moviedb_27.data.model.MoreGenre;
import com.framgia.moviedb_27.data.source.GenreDataSource;
import com.framgia.moviedb_27.utils.Constants;
import com.framgia.moviedb_27.utils.RetrofitData;
import io.reactivex.Observable;

public class GenreRemoteDataSource implements GenreDataSource.RemoteDataSource {

    private Api.GenreSource getData() {
        return RetrofitData.getDataFromUrl(Constants.BASE_URL)
                .create(Api.GenreSource.class);
    }

    @Override
    public Observable<MoreGenre> getGenreListData() {
        return getData().getGenreList();
    }
}
