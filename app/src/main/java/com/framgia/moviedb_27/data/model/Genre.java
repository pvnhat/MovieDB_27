package com.framgia.moviedb_27.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer mGenreId;
    @SerializedName("name")
    @Expose
    private String mGenreName;

    protected Genre(Parcel in) {
        if (in.readByte() == 0) {
            mGenreId = null;
        } else {
            mGenreId = in.readInt();
        }
        mGenreName = in.readString();
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mGenreId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mGenreId);
        }
        dest.writeString(mGenreName);
    }
}
