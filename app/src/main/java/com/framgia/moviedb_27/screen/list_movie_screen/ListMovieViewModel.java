package com.framgia.moviedb_27.screen.list_movie_screen;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.Toast;
import com.framgia.moviedb_27.BR;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_27.screen.detail.DetailActivity;
import com.framgia.moviedb_27.screen.main.MainViewModel;
import com.framgia.moviedb_27.screen.main.OnClickListener;
import com.framgia.moviedb_27.utils.Constants;
import com.framgia.moviedb_27.utils.TypeCategory;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListMovieViewModel extends BaseObservable
        implements OnClickListener.OnItemClickListener {

    private Context mContext;
    private ListMovieAdapter mListMovieAdapter;
    private MovieRepository.RemoteSource mRepositoryRemoteSource;
    private CompositeDisposable mCompositeDisposable;
    private String mKeyString, mValueString;
    private boolean mIsVisible;

    public ListMovieViewModel(Context context, ListMovieAdapter listMovieAdapter) {
        mContext = context;
        mListMovieAdapter = listMovieAdapter;
        mRepositoryRemoteSource = new MovieRepository.RemoteSource(new MovieRemoteDataSource());
        mCompositeDisposable = new CompositeDisposable();
        mListMovieAdapter.setOnClickListener(this);
    }

    protected void onStart() {
    }

    protected void onStop() {
        mCompositeDisposable.clear();
    }

    public void setQueryData(String key, String value) {
        mKeyString = key;
        mValueString = value;
        onInitDataRequest(Constants.NUM_ONE);
    }

    private void onInitDataRequest(int page) {
        Observable<MoreMovie> observable = null;
        if (mKeyString.equals(MainViewModel.CATEGORY_KEY)) {
            observable = checkCategory(page);
        } else if (mKeyString.equals(TypeCategory.GENRE)) {
            observable =
                    mRepositoryRemoteSource.getMovieByGenre(Integer.parseInt(mValueString), page);
        } else if (mKeyString.equals(TypeCategory.CAST) || mKeyString.equals(
                TypeCategory.POPULAR_ACTOR)) {
            observable =
                    mRepositoryRemoteSource.getMovieByCast(Integer.parseInt(mValueString), page);
        } else if (mKeyString.equals(TypeCategory.CREW)) {
            observable =
                    mRepositoryRemoteSource.getMovieByCrew(Integer.parseInt(mValueString), page);
        } else {
            observable = mRepositoryRemoteSource.getMovieBySearching(mValueString, page);
        }

        onRequestExcute(observable, page);
    }

    private Observable<MoreMovie> checkCategory(int page) {
        switch (mValueString) {
            case TypeCategory.POPULAR:
                return mRepositoryRemoteSource.getPopularMovie(page);
            case TypeCategory.NOW_PLAYING:
                return mRepositoryRemoteSource.getNowPlayingMovie(page);
            case TypeCategory.TOP_RATED:
                return mRepositoryRemoteSource.getTopRatedMovie(page);
            default:
                return mRepositoryRemoteSource.getUpcomingMovie(page);
        }
    }

    private void onRequestExcute(Observable<MoreMovie> observable, final int page) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoreMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MoreMovie moreMovie) {
                        if (page == Constants.NUM_ONE) {
                            mListMovieAdapter.updateMovieList(moreMovie.getMovieList());
                        } else {
                            mListMovieAdapter.loadMoreList(moreMovie.getMovieList());
                            setVisible(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext,
                                mContext.getString(R.string.text_error) + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public ListMovieAdapter getRecyclerAdapter() {
        return mListMovieAdapter;
    }

    @Bindable
    public boolean isVisible() {
        return mIsVisible;
    }

    public void setVisible(boolean visible) {
        mIsVisible = visible;
        notifyPropertyChanged(BR.visible);
    }

    public void fetchMoreData(int page) {
        onInitDataRequest(page);
    }

    @Override
    public void onItemClick(Object movie) {
        mContext.startActivity(DetailActivity.newInstance(mContext, (Movie) movie));
    }
}
