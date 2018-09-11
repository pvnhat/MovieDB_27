package com.framgia.moviedb_27.screen.main;

public interface OnClickListener {
    interface OnItemClickListener<T> {
        void onItemClick(T data);
    }
}
