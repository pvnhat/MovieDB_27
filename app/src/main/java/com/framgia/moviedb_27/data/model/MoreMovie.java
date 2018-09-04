package com.framgia.moviedb_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MoreMovie implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer mPage;
    @SerializedName("total_results")
    @Expose
    private Integer mTotalMovies;
    @SerializedName("total_pages")
    @Expose
    private Integer mTotalPages;
    @SerializedName("results")
    @Expose
    private List<Movie> mMovieList;
    public final static Parcelable.Creator<MoreMovie> CREATOR = new Creator<MoreMovie>() {

        @SuppressWarnings({
                "unchecked"
        })
        public MoreMovie createFromParcel(Parcel in) {
            return new MoreMovie(in);
        }

        public MoreMovie[] newArray(int size) {
            return (new MoreMovie[size]);
        }
    };

    protected MoreMovie(Parcel in) {
        mPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
        mTotalMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
        mTotalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(mMovieList, (com.framgia.moviedb_27.data.model.Movie.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public MoreMovie() {
    }

    /**
     *
     * @param movieList
     * @param totalMovies
     * @param page
     * @param totalPages
     */
    public MoreMovie(Integer page, Integer totalMovies, Integer totalPages, List<Movie> movieList) {
        super();
        mPage = page;
        mTotalMovies = totalMovies;
        mTotalPages = totalPages;
        mMovieList = movieList;
    }

    public Integer getPage() {
        return mPage;
    }

    public void setPage(Integer page) {
        mPage = page;
    }

    public Integer getTotalMovies() {
        return mTotalMovies;
    }

    public void setTotalMovies(Integer totalMovies) {
        mTotalMovies = totalMovies;
    }

    public Integer getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        mTotalPages = totalPages;
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mPage);
        dest.writeValue(mTotalMovies);
        dest.writeValue(mTotalPages);
        dest.writeList(mMovieList);
    }

    public int describeContents() {
        return 0;
    }
}
