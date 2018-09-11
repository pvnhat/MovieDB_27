package com.framgia.moviedb_27.screen.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.credit.Cast;
import com.framgia.moviedb_27.databinding.ItemActorBinding;
import com.framgia.moviedb_27.screen.main.OnClickListener;
import java.util.ArrayList;
import java.util.List;

public class DetailActorAdapter extends RecyclerView.Adapter<DetailActorAdapter.ViewHolder> {

    private List<Cast> mCastList;
    private OnClickListener.OnItemClickListener mOnItemClickListener;

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

    public void setItemClickListener(OnClickListener.OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemActorBinding itemActorBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_actor, parent, false);
        return new ViewHolder(itemActorBinding, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mCastList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCastList != null ? mCastList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemActorBinding mItemActorBinding;
        private OnClickListener.OnItemClickListener mItemClickListener;

        public ViewHolder(ItemActorBinding itemActorBinding,
                OnClickListener.OnItemClickListener itemClickListener) {
            super(itemActorBinding.getRoot());
            mItemActorBinding = itemActorBinding;
            mItemClickListener = itemClickListener;
        }

        public void bind(Cast cast) {
            mItemActorBinding.setCast(cast);
            mItemActorBinding.executePendingBindings();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mCastList.get(getAdapterPosition()).getIdInMovie());
            }
        }
    }
}
