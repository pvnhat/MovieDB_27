package com.framgia.moviedb_27.screen.list_movie_screen;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.databinding.MovieItemRowBinding;
import com.framgia.moviedb_27.screen.main.OnClickListener;
import java.util.ArrayList;
import java.util.List;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {

    private List<Movie> mMovieList;
    private OnClickListener.OnItemClickListener mOnItemClickListener;

    public ListMovieAdapter() {
        mMovieList = new ArrayList<>();
    }

    public void updateMovieList(List<Movie> movieList) {
        if (mMovieList != null) {
            mMovieList.clear();
        }
        mMovieList = movieList;
        notifyDataSetChanged();
    }

    public void loadMoreList(List<Movie> movieList) {
        if (movieList != null) {
            mMovieList.addAll(movieList);
            notifyDataSetChanged();
        }
    }

    public void setOnClickListener(OnClickListener.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemRowBinding movieItemRowBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.movie_item_row, parent, false);
        return new ViewHolder(movieItemRowBinding, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getViewHolder(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieList != null ? mMovieList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private MovieItemRowBinding mStudentRowBinding;
        private ItemRowViewModel mItemRowViewModel;

        ViewHolder(MovieItemRowBinding studentRowBinding, OnClickListener.OnItemClickListener itemClickListener) {
            super(studentRowBinding.getRoot());
            mStudentRowBinding = studentRowBinding;
            mItemRowViewModel = new ItemRowViewModel(itemClickListener);
            mStudentRowBinding.setViewModel(mItemRowViewModel);
        }

        void getViewHolder(Movie movie) {
            mItemRowViewModel.setItemData(movie);
            mStudentRowBinding.executePendingBindings();
        }
    }
}
