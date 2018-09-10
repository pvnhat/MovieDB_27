package com.framgia.moviedb_27.screen.detail;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;
import com.framgia.moviedb_27.BR;
import com.framgia.moviedb_27.data.model.MoreTrailer;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.data.model.credit.Cast;
import com.framgia.moviedb_27.data.model.credit.Credit;
import com.framgia.moviedb_27.data.model.credit.Crew;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.screen.youtube.YoutubeActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class DetailViewModel extends BaseObservable {
    private Movie mMovie;
    private Context mContext;
    private MovieRepository.RemoteSource mRemoteSource;
    private DetailActorAdapter mDetailActorAdapter;
    private DetailCrewAdapter mDetailCrewAdapter;
    private CompositeDisposable mCompositeDisposable;
    private boolean mIsVisibleProgressBar;
    private String mKey;

    DetailViewModel(Context context, Movie movie, DetailActorAdapter detailActorAdapter,
            DetailCrewAdapter detailCrewAdapter, MovieRepository.RemoteSource remoteSource) {
        mMovie = movie;
        mContext = context;
        mDetailActorAdapter = detailActorAdapter;
        mDetailCrewAdapter = detailCrewAdapter;
        mCompositeDisposable = new CompositeDisposable();
        mRemoteSource = remoteSource;
        loadData();
    }

    public void onClickePlay(View v) {
        mContext.startActivity(YoutubeActivity.getInstance(mContext, mKey));
    }

    public DetailActorAdapter getDetailActorAdapter() {
        return mDetailActorAdapter;
    }

    public DetailCrewAdapter getDetailCrewAdapter() {
        return mDetailCrewAdapter;
    }

    private void loadData() {
        getDataActorById(mMovie.getId());
        getKeybyMovieId(mMovie.getId());
    }

    private void getDataActorById(int idMovie) {
        Disposable disposable = mRemoteSource.getCreditByMovieId(idMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Credit>() {
                    @Override
                    public void accept(Credit credit) throws Exception {
                        onGetDataSuccess(credit.getCastList(), credit.getCrewList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMessage(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void getKeybyMovieId(int idMovie) {
        Disposable disposable = mRemoteSource.getTrailerList(idMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoreTrailer>() {
                    @Override
                    public void accept(MoreTrailer moreTrailer) throws Exception {
                        setVisibleProgressBar(true);
                        mKey = moreTrailer.getTrailerList().get(0).getKey();
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

    private void onGetDataSuccess(List<Cast> castList, List<Crew> crewList) {
        mDetailActorAdapter.updateCast(castList);
        mDetailCrewAdapter.updateCrew(crewList);
    }

    public Movie getMovie() {
        return mMovie;
    }

    @Bindable
    public boolean isVisibleProgressBar() {
        return mIsVisibleProgressBar;
    }

    private void setVisibleProgressBar(boolean visibleProgressBar) {
        mIsVisibleProgressBar = visibleProgressBar;
        notifyPropertyChanged(BR.visibleProgressBar);
    }
}
