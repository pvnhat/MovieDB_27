package com.framgia.moviedb_27.data.repository;

import com.framgia.moviedb_27.data.model.MoreGenre;
import com.framgia.moviedb_27.data.source.GenreDataSource;
import com.framgia.moviedb_27.data.source.remote.GenreRemoteDataSource;
import io.reactivex.Observable;

public class GenreRepository {
    private GenreDataSource.RemoteDataSource mRemoteDataSource;

    public GenreRepository() {
        mRemoteDataSource = new GenreRemoteDataSource();
    }

    public Observable<MoreGenre> getRemoteGenreDataSource() {
        return mRemoteDataSource.getGenreListData();
    }
}
