package com.framgia.moviedb_27.screen.main;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.screen.BaseViewModel;
import com.framgia.moviedb_27.screen.detail.DetailActivity;
import com.framgia.moviedb_27.screen.list_movie_screen.ListMovieActivity;
import com.framgia.moviedb_27.utils.Constants;
import com.framgia.moviedb_27.utils.TypeCategory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MainViewModel extends BaseViewModel implements OnClickListener.OnItemClickListener {

    public static String CATEGORY_KEY = "category";

    private Context mContext;
    private MovieRepository.RemoteSource mRemoteSource;
    private MovieAdapter mMovieAdapterPopular, mMovieAdapterTopRate, mMovieAdapterUpcoming,
            mMovieAdapterNowplaying;
    private MainPagerAdapter mMainPagerAdapter;
    private CompositeDisposable mCompositeDisposable;

    public MainViewModel(Context context, MovieRepository.RemoteSource remoteSource,
            MovieAdapter movieAdapterPopular, MovieAdapter movieAdapterTopRate,
            MovieAdapter movieAdapterUpcoming, MovieAdapter movieAdapterNowplaying,
            MainPagerAdapter mainPagerAdapter) {
        mContext = context;
        mRemoteSource = remoteSource;
        mMovieAdapterPopular = movieAdapterPopular;
        mMovieAdapterTopRate = movieAdapterTopRate;
        mMovieAdapterUpcoming = movieAdapterUpcoming;
        mMovieAdapterNowplaying = movieAdapterNowplaying;
        mCompositeDisposable = new CompositeDisposable();
        mMainPagerAdapter = mainPagerAdapter;
        mMovieAdapterPopular.setOnItemClick(this);
        mMovieAdapterTopRate.setOnItemClick(this);
        mMovieAdapterUpcoming.setOnItemClick(this);
        mMovieAdapterNowplaying.setOnItemClick(this);
        loadData();
    }

    public MovieAdapter getMovieAdapterPopular() {
        return mMovieAdapterPopular;
    }

    public MovieAdapter getMovieAdapterUpcoming() {
        return mMovieAdapterUpcoming;
    }

    public MovieAdapter getMovieAdapterNowplaying() {
        return mMovieAdapterNowplaying;
    }

    public MovieAdapter getMovieAdapterTopRate() {
        return mMovieAdapterTopRate;
    }

    private void getDataMoviePopular(int numPage) {
        Disposable disposable = mRemoteSource.getPopularMovie(numPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoreMovie>() {
                    @Override
                    public void accept(MoreMovie moreMovie) throws Exception {
                        onGetDataSuccess(moreMovie.getMovieList(), Constants.POPULAR_MOVIE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMessageThrowable(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void getDataMovieTopRate(int numPage) {
        Disposable disposable = mRemoteSource.getTopRatedMovie(numPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoreMovie>() {
                    @Override
                    public void accept(MoreMovie moreMovie) throws Exception {
                        onGetDataSuccess(moreMovie.getMovieList(), Constants.TOP_RATE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMessageThrowable(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void getDataMovieUpComing(int numPage) {
        Disposable disposable = mRemoteSource.getUpcomingMovie(numPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoreMovie>() {
                    @Override
                    public void accept(MoreMovie moreMovie) throws Exception {
                        onGetDataSuccess(moreMovie.getMovieList(), Constants.UP_COMING);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMessageThrowable(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void getDataMovieNowPlaying(int numPage) {
        Disposable disposable = mRemoteSource.getNowPlayingMovie(numPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoreMovie>() {
                    @Override
                    public void accept(MoreMovie moreMovie) throws Exception {
                        onGetDataSuccess(moreMovie.getMovieList(), Constants.NOW_PLAYING);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMessageThrowable(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void onGetDataSuccess(List<Movie> movies, String Genre) {
        if (movies != null) {
            switch (Genre) {
                case Constants.POPULAR_MOVIE:
                    mMovieAdapterPopular.updateMovie(movies);
                    return;
                case Constants.TOP_RATE:
                    mMovieAdapterTopRate.updateMovie(movies);
                    return;
                case Constants.UP_COMING:
                    mMovieAdapterUpcoming.updateMovie(movies);
                    return;
                case Constants.NOW_PLAYING:
                    mMovieAdapterNowplaying.updateMovie(movies);
                    return;
            }
        }
    }

    public void getMessageThrowable(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
    }

    private void loadData() {
        getDataMoviePopular(Constants.NUM_ONE);
        getDataMovieNowPlaying(Constants.NUM_ONE);
        getDataMovieTopRate(Constants.NUM_ONE);
        getDataMovieUpComing(Constants.NUM_ONE);
    }

    public MainPagerAdapter getMainPagerAdapter() {
        return mMainPagerAdapter;
    }

    @Override
    protected void onStop() {
    }

    public void onLoadMoreClicked(View view) {
        String category = "";
        switch (view.getId()) {
            case R.id.button_popular:
                category = TypeCategory.POPULAR;
                break;
            case R.id.button_nowplaying:
                category = TypeCategory.NOW_PLAYING;
                break;
            case R.id.button_toprated:
                category = TypeCategory.TOP_RATED;
                break;
            case R.id.button_upcoming:
                category = TypeCategory.UP_COMING;
                break;
        }
        mContext.startActivity(ListMovieActivity.newInstance(mContext, CATEGORY_KEY, category));
    }

    @Override
    public void onItemClick(Object movie) {
        mContext.startActivity(DetailActivity.newInstance(mContext, (Movie) movie));
    }
}
