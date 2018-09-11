package com.framgia.moviedb_27.screen.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.moviedb_27.R;
import com.framgia.moviedb_27.data.model.credit.Crew;
import com.framgia.moviedb_27.databinding.ItemCrewBinding;
import com.framgia.moviedb_27.screen.main.OnClickListener;
import java.util.ArrayList;
import java.util.List;

public class DetailCrewAdapter extends RecyclerView.Adapter<DetailCrewAdapter.ViewHolder> {

    private List<Crew> mCrewList;
    private OnClickListener.OnItemClickListener mOnItemClickListener;

    DetailCrewAdapter() {
        mCrewList = new ArrayList<>();
    }

    public void updateCrew(List<Crew> crews) {
        if (mCrewList != null) {
            mCrewList.clear();
        }
        mCrewList.addAll(crews);
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnClickListener.OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCrewBinding itemCrewBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_crew, parent, false);
        return new ViewHolder(itemCrewBinding, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mCrewList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCrewList != null ? mCrewList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemCrewBinding mItemCrewBinding;
        private OnClickListener.OnItemClickListener mOnItemClickListener;

        public ViewHolder(ItemCrewBinding itemCrewBinding,
                OnClickListener.OnItemClickListener onItemClickListener) {
            super(itemCrewBinding.getRoot());
            mItemCrewBinding = itemCrewBinding;
            mOnItemClickListener = onItemClickListener;
        }

        public void bind(Crew crew) {
            mItemCrewBinding.setCrew(crew);
            mItemCrewBinding.executePendingBindings();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(
                        mCrewList.get(getAdapterPosition()).getIdInMovie());
            }
        }
    }
}
