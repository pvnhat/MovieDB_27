package com.framgia.moviedb_27.data.model.credit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Credit {
    @SerializedName("id")
    @Expose
    private Integer mCreditId;
    @SerializedName("cast")
    @Expose
    private List<Cast> mCastList;
    @SerializedName("crew")
    @Expose
    private List<Crew> mCrewList;

    public Credit() {
    }

    public Credit(Integer creditId, List<Cast> castList, List<Crew> crewList) {
        super();
        mCreditId = creditId;
        mCastList = castList;
        mCrewList = crewList;
    }

    public Integer getCreditId() {
        return mCreditId;
    }

    public void setCreditId(Integer creditId) {
        mCreditId = creditId;
    }

    public List<Cast> getCastList() {
        return mCastList;
    }

    public void setCastList(List<Cast> castList) {
        mCastList = castList;
    }

    public List<Crew> getCrewList() {
        return mCrewList;
    }

    public void setCrewList(List<Crew> crewList) {
        mCrewList = crewList;
    }
}
