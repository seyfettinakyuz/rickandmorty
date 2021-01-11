package com.akyuz.rickandmorty.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Location implements Parcelable {
    @SerializedName("name")
    private final String name;

    protected Location(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public String getName() {
        return name;
    }
}
