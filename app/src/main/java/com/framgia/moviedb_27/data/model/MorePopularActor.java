package com.framgia.moviedb_27.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MorePopularActor {
    @SerializedName("page")
    @Expose
    private Integer mPage;
    @SerializedName("total_PopularActors")
    @Expose
    private Integer mTotalPopularActors;
    @SerializedName("total_pages")
    @Expose
    private Integer mTotalPages;
    @SerializedName("results")
    @Expose
    private List<PopularActor> mPopularActorList;

    public MorePopularActor() {
    }

    public MorePopularActor(Integer page, Integer totalPopularActors, Integer totalPages,
            List<PopularActor> popularActorList) {
        super();
        mPage = page;
        mTotalPopularActors = totalPopularActors;
        mTotalPages = totalPages;
        mPopularActorList = popularActorList;
    }

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        mPage = page;
    }

    public Integer getTotalPopularActors() {
        return mTotalPopularActors;
    }

    public void setTotalPopularActors(Integer totalPopularActors) {
        mTotalPopularActors = totalPopularActors;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        mTotalPages = totalPages;
    }

    public List<PopularActor> getPopularActorList() {
        return mPopularActorList;
    }

    public void setPopularActorList(List<PopularActor> popularActorList) {
        mPopularActorList = popularActorList;
    }
}
