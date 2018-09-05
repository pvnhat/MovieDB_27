package com.framgia.moviedb_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MoreTrailer implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer mMoreTrailerId;
    @SerializedName("results")
    @Expose
    private List<Trailer> mTrailerList;

    /**
     * No args constructor for use in serialization
     */
    public MoreTrailer() {
    }

    public MoreTrailer(Integer moreTrailerId, List<Trailer> trailerList) {
        super();
        mMoreTrailerId = moreTrailerId;
        mTrailerList = trailerList;
    }

    protected MoreTrailer(Parcel in) {
        if (in.readByte() == 0) {
            mMoreTrailerId = null;
        } else {
            mMoreTrailerId = in.readInt();
        }
        mTrailerList = in.createTypedArrayList(Trailer.CREATOR);
    }

    public static final Creator<MoreTrailer> CREATOR = new Creator<MoreTrailer>() {
        @Override
        public MoreTrailer createFromParcel(Parcel in) {
            return new MoreTrailer(in);
        }

        @Override
        public MoreTrailer[] newArray(int size) {
            return new MoreTrailer[size];
        }
    };

    public Integer getMoreTrailerId() {
        return mMoreTrailerId;
    }

    public void setMoreTrailerId(Integer moreTrailerId) {
        mMoreTrailerId = moreTrailerId;
    }

    public List<Trailer> getTrailerList() {
        return mTrailerList;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        mTrailerList = trailerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (mMoreTrailerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(mMoreTrailerId);
        }
        parcel.writeTypedList(mTrailerList);
    }
}
