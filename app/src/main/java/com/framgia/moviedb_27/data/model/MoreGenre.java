package com.framgia.moviedb_27.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MoreGenre {

    @SerializedName("genres")
    @Expose
    private List<Genre> mGenreList;

    public List<Genre> getGenres() {
        return mGenreList;
    }

    public void setGenres(List<Genre> genres) {
        mGenreList = genres;
    }
}
