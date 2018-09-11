package com.framgia.moviedb_27.screen.genre;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.Genre;
import com.framgia.moviedb_27.databinding.ItemGenreBinding;
import com.framgia.moviedb_27.screen.ItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private List<Genre> mGenres;
    private ItemClickListener mItemClickListener;

    GenreAdapter() {
        mGenres = new ArrayList<>();
    }

    public void updateGenre(List<Genre> genres) {
        if (mGenres != null) {
            mGenres.clear();
        }
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenreBinding itemGenreBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_genre, parent, false);
        return new ViewHolder(itemGenreBinding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder holder, int position) {
        holder.bind(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemGenreBinding mItemGenreBinding;
        private ItemClickListener mItemClickListener;

        public ViewHolder(ItemGenreBinding itemGenreBinding, ItemClickListener itemClickListener) {
            super(itemGenreBinding.getRoot());
            mItemGenreBinding = itemGenreBinding;
            mItemClickListener = itemClickListener;
        }

        public void bind(Genre genre) {
            mItemGenreBinding.setGenre(genre);
            mItemGenreBinding.executePendingBindings();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onClick(mGenres.get(getAdapterPosition()).getId());
            }
        }
    }
}
