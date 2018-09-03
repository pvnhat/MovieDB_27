package com.framgia.moviedb_27.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("id")
    @Expose
    private Integer mGenreId;
    @SerializedName("name")
    @Expose
    private String mGenreName;

    public Integer getId() {
        return mGenreId;
    }

    public void setId(Integer id) {
        mGenreId = id;
    }

    public String getName() {
        return mGenreName;
    }

    public void setName(String name) {
        mGenreName = name;
    }
}
