package com.framgia.moviedb_27.screen.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.Movie;
import com.framgia.moviedb_27.databinding.ItemRecycleviewBinding;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> mMovies;
    private OnClickListener.OnItemClickListener mOnItemClickListener;

    public MovieAdapter() {
        mMovies = new ArrayList<>();
    }

    public void updateMovie(List<Movie> movies) {
        if (mMovies != null) {
            mMovies.clear();
        }
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnClickListener.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecycleviewBinding itemRecycleviewBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_recycleview, parent, false);
        return new ViewHolder(itemRecycleviewBinding, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, final int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemRecycleviewBinding mItemRecycleviewBinding;
        private OnClickListener.OnItemClickListener mOnItemClickListener;
        private Movie mMovie;

        public ViewHolder(ItemRecycleviewBinding itemRecycleviewBinding,
                OnClickListener.OnItemClickListener onItemClickListener) {
            super(itemRecycleviewBinding.getRoot());
            mItemRecycleviewBinding = itemRecycleviewBinding;
            mOnItemClickListener = onItemClickListener;
        }

        public void bind(Movie movie) {
            mItemRecycleviewBinding.setMovie(movie);
            mItemRecycleviewBinding.executePendingBindings();
            mMovie = movie;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mMovie != null && mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(mMovie);
            }
        }
    }
}
