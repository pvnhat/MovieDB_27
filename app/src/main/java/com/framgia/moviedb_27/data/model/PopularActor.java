package com.framgia.moviedb_27.data.model;

import com.framgia.moviedb_27.utils.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PopularActor {
    @SerializedName("popularity")
    @Expose
    private Double mPopularity;
    @SerializedName("id")
    @Expose
    private Integer mActorId;
    @SerializedName("profile_path")
    @Expose
    private String mProfilePath;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("known_for")
    @Expose
    private List<Movie> mMovieList;

    public PopularActor() {
    }

    public PopularActor(Double popularity, Integer actorId, String profilePath, String name,
            List<Movie> movieList) {
        super();
        mPopularity = popularity;
        mActorId = actorId;
        mProfilePath = profilePath;
        mName = name;
        mMovieList = movieList;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public Integer getActorId() {
        return mActorId;
    }

    public void setActorId(Integer actorId) {
        mActorId = actorId;
    }

    public String getProfilePath() {
        return Constants.BASE_IMAGE_LINK + mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
    }
}
