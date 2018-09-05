package com.framgia.moviedb_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer implements Parcelable {
    @SerializedName("id")
    @Expose
    private String mTrailerId;
    @SerializedName("key")
    @Expose
    private String mKey;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("site")
    @Expose
    private String mSite;
    @SerializedName("size")
    @Expose
    private Integer mSize;
    @SerializedName("type")
    @Expose
    private String mType;

    /**
     * No args constructor for use in serialization
     */
    public Trailer() {
    }

    public Trailer(String trailerId, String key, String name, String site, Integer size,
            String type) {
        super();
        mTrailerId = trailerId;
        mKey = key;
        mName = name;
        mSite = site;
        mSize = size;
        mType = type;
    }

    protected Trailer(Parcel in) {
        mTrailerId = in.readString();
        mKey = in.readString();
        mName = in.readString();
        mSite = in.readString();
        if (in.readByte() == 0) {
            mSize = null;
        } else {
            mSize = in.readInt();
        }
        mType = in.readString();
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    public String getTrailerId() {
        return mTrailerId;
    }

    public void setTrailerId(String trailerId) {
        mTrailerId = trailerId;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    public Integer getSize() {
        return mSize;
    }

    public void setSize(Integer size) {
        mSize = size;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTrailerId);
        parcel.writeString(mKey);
        parcel.writeString(mName);
        parcel.writeString(mSite);
        if (mSize == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(mSize);
        }
        parcel.writeString(mType);
    }
}
