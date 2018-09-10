package com.framgia.moviedb_27.screen.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.credit.Cast;
import com.framgia.moviedb_27.databinding.ItemActorBinding;
import java.util.ArrayList;
import java.util.List;

public class DetailActorAdapter extends RecyclerView.Adapter<DetailActorAdapter.ViewHolder> {

   private List<Cast> mCastList;

    DetailActorAdapter() {
        mCastList = new ArrayList<>();
    }

    public void updateCast(List<Cast> casts) {
        if (mCastList != null) {
            mCastList.clear();
        }
        mCastList.addAll(casts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemActorBinding itemActorBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_actor, parent, false);
        return new ViewHolder(itemActorBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mCastList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCastList != null ? mCastList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemActorBinding mItemActorBinding;

        public ViewHolder(ItemActorBinding itemActorBinding) {
            super(itemActorBinding.getRoot());
            mItemActorBinding = itemActorBinding;
        }

        public void bind(Cast cast) {
            mItemActorBinding.setCast(cast);
            mItemActorBinding.executePendingBindings();
        }
    }
}
