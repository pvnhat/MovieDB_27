package com.framgia.moviedb_27.screen.actor;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.PopularActor;
import com.framgia.moviedb_27.databinding.ItemPopularActorBinding;
import com.framgia.moviedb_27.screen.ItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {
    private List<PopularActor> mPopularActors;
    private ItemClickListener mItemClickListener;

    public ActorAdapter() {
        mPopularActors = new ArrayList<>();
    }

    public void updateActor(List<PopularActor> popularActors) {
        if (mPopularActors != null) {
            mPopularActors.clear();
        }
        mPopularActors.addAll(popularActors);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void addDataLoadMoreActor(List<PopularActor> popularActors) {
        mPopularActors.addAll(popularActors);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ActorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPopularActorBinding itemPopularActorBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_popular_actor, parent, false);
        return new ActorAdapter.ViewHolder(itemPopularActorBinding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mPopularActors.get(position));
    }

    @Override
    public int getItemCount() {
        return mPopularActors != null ? mPopularActors.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemPopularActorBinding mItemPopularActorBinding;
        private ItemClickListener mItemClickListener;

        public ViewHolder(ItemPopularActorBinding itemPopularActorBinding,
                ItemClickListener itemClickListener) {
            super(itemPopularActorBinding.getRoot());
            mItemPopularActorBinding = itemPopularActorBinding;
            mItemClickListener = itemClickListener;
        }

        public void bind(PopularActor popularActor) {
            mItemPopularActorBinding.setPopularActor(popularActor);
            mItemPopularActorBinding.executePendingBindings();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onClick(mPopularActors.get(getAdapterPosition()).getActorId());
            }
        }
    }
}
