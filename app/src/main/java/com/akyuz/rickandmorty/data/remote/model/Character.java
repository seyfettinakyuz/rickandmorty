package com.akyuz.rickandmorty.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Character implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String status;
    @SerializedName("species")
    private String species;
    @SerializedName("gender")
    private String gender;
    @SerializedName("origin")
    private Location originLocation;
    @SerializedName("location")
    private Location lastLocation;
    @SerializedName("image")
    private String image;
    @SerializedName("episode")
    private List<String> episodeList;

    public Character() {
    }

    protected Character(Parcel in) {
        id = in.readInt();
        name = in.readString();
        status = in.readString();
        species = in.readString();
        gender = in.readString();
        originLocation = in.readParcelable(Location.class.getClassLoader());
        lastLocation = in.readParcelable(Location.class.getClassLoader());
        image = in.readString();
        episodeList = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(status);
        dest.writeString(species);
        dest.writeString(gender);
        dest.writeParcelable(originLocation, flags);
        dest.writeParcelable(lastLocation, flags);
        dest.writeString(image);
        dest.writeStringList(episodeList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public Location getOriginLocation() {
        return originLocation;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public String getImage() {
        return image;
    }

    public List<String> getEpisodeList() {
        return episodeList;
    }

    public String recordInfo(){
        return id + " - " + name;
    }
}
