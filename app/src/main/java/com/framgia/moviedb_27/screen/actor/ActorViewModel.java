package com.framgia.moviedb_27.screen.actor;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;
import com.framgia.moviedb_27.BR;
import com.framgia.moviedb_27.data.model.MorePopularActor;
import com.framgia.moviedb_27.data.model.PopularActor;
import com.framgia.moviedb_27.data.repository.MovieRepository;
import com.framgia.moviedb_27.screen.ItemClickListener;
import com.framgia.moviedb_27.utils.Constants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ActorViewModel extends BaseObservable implements ItemClickListener {

    private Context mContext;
    private MovieRepository.RemoteSource mRemoteSource;
    private ActorAdapter mActorAdapter;
    private CompositeDisposable mCompositeDisposable;
    private int mNumPage;
    private boolean mIsVisible;

    public ActorViewModel(Context context, MovieRepository.RemoteSource remoteSource,
            ActorAdapter ActorAdapter) {
        mContext = context;
        mRemoteSource = remoteSource;
        mActorAdapter = ActorAdapter;
        mActorAdapter.setItemClickListener(this);
        mCompositeDisposable = new CompositeDisposable();
        loadData();
    }

    public ActorAdapter getDetailActorAdapter() {
        return mActorAdapter;
    }

    private void loadData() {
        mNumPage = Constants.NUM_ONE;
        getListByNumPage(mNumPage);
        mNumPage++;
    }

    private void getListByNumPage(int numPage) {
        Disposable disposable = mRemoteSource.getPopularPersons(numPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MorePopularActor>() {
                    @Override
                    public void accept(MorePopularActor morePopularActor) throws Exception {
                        setVisible(true);
                        onDataSuccess(morePopularActor.getPopularActorList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMessage(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void onClickMore(View v) {
        getListByNumPage(mNumPage);
        mNumPage++;
    }

    private void getMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    private void onDataSuccess(List<PopularActor> popularActors) {
        mActorAdapter.addDataLoadMoreActor(popularActors);
    }

    @Override
    public void onClick(int id) {
    }

    @Bindable
    public boolean isVisible() {
        return mIsVisible;
    }

    public void setVisible(boolean visible) {
        mIsVisible = visible;
        notifyPropertyChanged(BR.visible);
    }
}
