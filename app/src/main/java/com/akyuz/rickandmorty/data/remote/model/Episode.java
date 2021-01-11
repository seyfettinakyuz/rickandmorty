package com.akyuz.rickandmorty.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Episode implements Parcelable {
    @SerializedName("name")
    String name;
    @SerializedName("air_date")
    String airDate;

    public Episode() {
    }

    public Episode(String name, String airDate) {
        this.name = name;
        this.airDate = airDate;
    }

    protected Episode(Parcel in) {
        name = in.readString();
        airDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(airDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Episode> CREATOR = new Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAirDate() {
        return airDate;
    }
}
