package com.framgia.moviedb_27.screen.genre;

import android.content.Context;
import android.widget.Toast;
import com.framgia.moviedb_27.data.model.Genre;
import com.framgia.moviedb_27.data.model.MoreGenre;
import com.framgia.moviedb_27.data.repository.GenreRepository;
import com.framgia.moviedb_27.screen.BaseViewModel;
import com.framgia.moviedb_27.screen.ItemClickListener;
import com.framgia.moviedb_27.screen.list_movie_screen.ListMovieActivity;
import com.framgia.moviedb_27.utils.TypeCategory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class GenreViewModel extends BaseViewModel implements ItemClickListener {
    private Context mContext;
    private GenreRepository mGenreRepository;
    private CompositeDisposable mCompositeDisposable;
    private GenreAdapter mGenreAdapter;

    GenreViewModel(Context context, GenreRepository genreRepository, GenreAdapter genreAdapter) {
        mContext = context;
        mGenreRepository = genreRepository;
        mGenreAdapter = genreAdapter;
        mGenreAdapter.setItemClickListener(this);
        mCompositeDisposable = new CompositeDisposable();
        getListByNumPage();
    }

    public GenreAdapter getGenreAdapter() {
        return mGenreAdapter;
    }

    private void getListByNumPage() {
        Disposable disposable = mGenreRepository.getRemoteGenreDataSource()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoreGenre>() {
                    @Override
                    public void accept(MoreGenre moreGenre) throws Exception {
                        onDataSuccess(moreGenre.getGenres());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMessage(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void getMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    private void onDataSuccess(List<Genre> genres) {
        mGenreAdapter.updateGenre(genres);
    }

    @Override
    protected void onStart() {
    }

    @Override
    protected void onStop() {
    }

    @Override
    public void onClick(int id) {
        mContext.startActivity(
                ListMovieActivity.newInstance(mContext, TypeCategory.GENRE, String.valueOf(id)));
    }
}
