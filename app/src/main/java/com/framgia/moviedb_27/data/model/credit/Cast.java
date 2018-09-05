package com.framgia.moviedb_27.data.model.credit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cast {
    @SerializedName("cast_id")
    @Expose
    private Integer mCastId;
    @SerializedName("character")
    @Expose
    private String mCharacter;
    @SerializedName("credit_id")
    @Expose
    private String mCreditId;
    @SerializedName("gender")
    @Expose
    private Integer mGender;
    @SerializedName("id")
    @Expose
    private Integer mIdInMovie;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("order")
    @Expose
    private Integer mOrder;
    @SerializedName("profile_path")
    @Expose
    private String mProfilePath;

    /**
     * No args constructor for use in serialization
     */
    public Cast() {
    }

    /**
     *
     * @param profilePath
     * @param order
     * @param castId
     * @param name
     * @param gender
     * @param creditId
     * @param character
     */
    public Cast(Integer castId, String character, String creditId, Integer gender,
            Integer idInMovie, String name, Integer order, String profilePath) {
        super();
        mCastId = castId;
        mCharacter = character;
        mCreditId = creditId;
        mGender = gender;
        mIdInMovie = idInMovie;
        mName = name;
        mOrder = order;
        mProfilePath = profilePath;
    }

    public Integer getCastId() {
        return mCastId;
    }

    public void setCastId(Integer castId) {
        mCastId = castId;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public Integer getGender() {
        return mGender;
    }

    public void setGender(Integer gender) {
        mGender = gender;
    }

    public Integer getIdInMovie() {
        return mIdInMovie;
    }

    public void setIdInMovie(Integer idInMovie) {
        mIdInMovie = idInMovie;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Integer getOrder() {
        return mOrder;
    }

    public void setOrder(Integer order) {
        mOrder = order;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }
}
