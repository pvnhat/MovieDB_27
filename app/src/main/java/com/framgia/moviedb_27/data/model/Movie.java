package com.framgia.moviedb_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.framgia.moviedb_27.utils.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Movie {
    @SerializedName("vote_count")
    @Expose
    private Integer mVoteCount;
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("vote_average")
    @Expose
    private Double mVoteAverage;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;
    @SerializedName("original_language")
    @Expose
    private String mOriginalLanguage;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> mGenreIds;
    @SerializedName("backdrop_path")
    @Expose
    private String mBackdropPath;
    @SerializedName("overview")
    @Expose
    private String mOverview;
    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;
    public final static Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }
    };

    protected Movie(Parcel in) {
        mVoteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        mId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        mVoteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
        mTitle = ((String) in.readValue((String.class.getClassLoader())));
        mPosterPath = ((String) in.readValue((String.class.getClassLoader())));
        mOriginalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(mGenreIds, (java.lang.Integer.class.getClassLoader()));
        mBackdropPath = ((String) in.readValue((String.class.getClassLoader())));
        mOverview = ((String) in.readValue((String.class.getClassLoader())));
        mReleaseDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public String getImageUrl() {
        return Constants.BASE_IMAGE_LINK + getPosterPath();
    }

    /**
     *
     * @param genreIds
     * @param id
     * @param title
     * @param releaseDate
     * @param overview
     * @param posterPath
     * @param voteAverage
     * @param originalLanguage
     * @param backdropPath
     * @param voteCount
     * @param
     */
    public Movie(Integer voteCount, Integer id, Double voteAverage, String title, String posterPath,
            String originalLanguage, List<Integer> genreIds, String backdropPath, String overview,
            String releaseDate) {
        super();
        mVoteCount = voteCount;
        mId = id;
        mVoteAverage = voteAverage;
        mTitle = title;
        mPosterPath = posterPath;
        mOriginalLanguage = originalLanguage;
        mGenreIds = genreIds;
        mBackdropPath = backdropPath;
        mOverview = overview;
        mReleaseDate = releaseDate;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Integer voteCount) {
        mVoteCount = voteCount;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        mGenreIds = genreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mVoteCount);
        dest.writeValue(mId);
        dest.writeValue(mVoteAverage);
        dest.writeValue(mTitle);
        dest.writeValue(mPosterPath);
        dest.writeValue(mOriginalLanguage);
        dest.writeList(mGenreIds);
        dest.writeValue(mBackdropPath);
        dest.writeValue(mOverview);
        dest.writeValue(mReleaseDate);
    }

    public int describeContents() {
        return 0;
    }
}
