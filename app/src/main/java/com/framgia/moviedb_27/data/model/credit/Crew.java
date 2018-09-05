package com.framgia.moviedb_27.data.model.credit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crew {
    @SerializedName("credit_id")
    @Expose
    private String mCreditId;
    @SerializedName("department")
    @Expose
    private String mDepartment;
    @SerializedName("gender")
    @Expose
    private Integer mGender;
    @SerializedName("id")
    @Expose
    private Integer mIdInMovie;
    @SerializedName("job")
    @Expose
    private String mJob;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("profile_path")
    @Expose
    private Object mProfilePath;

    /**
     * No args constructor for use in serialization
     */
    public Crew() {
    }

    /**
     * @param profilePath
     * @param department
     * @param name
     * @param job
     * @param gender
     * @param creditId
     */
    public Crew(String creditId, String department, Integer gender, Integer idInMovie, String job,
            String name, Object profilePath) {
        super();
        mCreditId = creditId;
        mDepartment = department;
        mGender = gender;
        mIdInMovie = idInMovie;
        mJob = job;
        mName = name;
        mProfilePath = profilePath;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String department) {
        mDepartment = department;
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

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Object getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(Object profilePath) {
        mProfilePath = profilePath;
    }
}
