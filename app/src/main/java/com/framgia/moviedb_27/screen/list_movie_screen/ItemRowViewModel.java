package com.framgia.moviedb_27.screen.list_movie_screen;

import android.databinding.ObservableField;
import android.view.View;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.screen.main.OnClickListener;
import com.framgia.moviedb_27.utils.Constants;

public class ItemRowViewModel {
    public ObservableField<Movie> mMovieObservableField;
    private OnClickListener.OnItemClickListener mOnItemClickListener;

    public ItemRowViewModel(OnClickListener.OnItemClickListener onItemClickListener) {
        mMovieObservableField = new ObservableField<>();
        mOnItemClickListener = onItemClickListener;
    }

    public String getImageUrl() {
        return mMovieObservableField.get().getPosterPath();
    }

    public String getVoteAverage() {
        return mMovieObservableField.get().getVoteAverage().toString();
    }

    public void setItemData(Movie movie) {
        mMovieObservableField.set(movie);
    }

    public void onItemClicked(View view) {
        if (mOnItemClickListener == null || mMovieObservableField.get() == null) {
            return;
        }
        mOnItemClickListener.onItemClick(mMovieObservableField.get());
    }
}
