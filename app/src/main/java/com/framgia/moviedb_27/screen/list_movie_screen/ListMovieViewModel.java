package com.framgia.moviedb_27.screen.list_movie_screen;

import android.content.Context;
import android.widget.Toast;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.MoreMovie;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.data.source.remote.MovieRemoteDataSource;
import com.framgia.moviedb_27.screen.BaseViewModel;
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

public class ListMovieViewModel extends BaseViewModel
        implements OnClickListener.OnItemClickListener {

    private Context mContext;
    private ListMovieAdapter mListMovieAdapter;
    private MovieRepository.RemoteSource mRepositoryRemoteSource;
    private CompositeDisposable mCompositeDisposable;
    private String mKeyString, mValueString;

    public ListMovieViewModel(Context context, ListMovieAdapter listMovieAdapter) {
        mContext = context;
        mListMovieAdapter = listMovieAdapter;
        mRepositoryRemoteSource = new MovieRepository.RemoteSource(new MovieRemoteDataSource());
        mCompositeDisposable = new CompositeDisposable();
        mListMovieAdapter.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
    }

    public void setQueryData(String key, String value) {
        mKeyString = key;
        mValueString = value;
        onInitDataRequest();
    }

    private void onInitDataRequest() {
        Observable<MoreMovie> observable = null;
        if (mKeyString.equals(MainViewModel.CATEGORY_KEY)) {
            observable = checkCategory();
        } else if (mKeyString.equals(TypeCategory.GENRE)) {
            observable = mRepositoryRemoteSource.getMovieByGenre(Constants.NUM_ONE,
                    Integer.parseInt(mValueString));
        } else if (mKeyString.equals(TypeCategory.CAST)) {
            observable = mRepositoryRemoteSource.getMovieByCast(Constants.NUM_ONE,
                    Integer.parseInt(mValueString));
        } else if (mKeyString.equals(TypeCategory.CREW)) {
            observable = mRepositoryRemoteSource.getMovieByCrew(Constants.NUM_ONE,
                    Integer.parseInt(mValueString));
        } else {
            observable =
                    mRepositoryRemoteSource.getMovieBySearching(mValueString, Constants.NUM_ONE);
        }

        onRequestExcute(observable);
    }

    private Observable<MoreMovie> checkCategory() {
        switch (mValueString) {
            case TypeCategory.POPULAR:
                return mRepositoryRemoteSource.getPopularMovie(Constants.NUM_ONE);
            case TypeCategory.NOW_PLAYING:
                return mRepositoryRemoteSource.getNowPlayingMovie(Constants.NUM_ONE);
            case TypeCategory.TOP_RATED:
                return mRepositoryRemoteSource.getTopRatedMovie(Constants.NUM_ONE);
            default:
                return mRepositoryRemoteSource.getUpcomingMovie(Constants.NUM_ONE);
        }
    }

    private void onRequestExcute(Observable<MoreMovie> observable) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoreMovie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MoreMovie moreMovie) {
                        mListMovieAdapter.updateMovieList(moreMovie.getMovieList());
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

    @Override
    public void onItemClick(Object movie) {
        mContext.startActivity(DetailActivity.newInstance(mContext, (Movie) movie));
    }
}
