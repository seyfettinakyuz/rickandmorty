package com.akyuz.rickandmorty.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CharacterInfo implements Parcelable {
    private int characterId;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String originLocationName;
    private String lastLocationName;
    private String image;
    private int numberOfEpisodes;
    private String lastSeenEpisodeName;
    private String lastSeenEpisodeAirDate;

    public CharacterInfo() {
    }


    protected CharacterInfo(Parcel in) {
        characterId = in.readInt();
        name = in.readString();
        status = in.readString();
        species = in.readString();
        gender = in.readString();
        originLocationName = in.readString();
        lastLocationName = in.readString();
        image = in.readString();
        numberOfEpisodes = in.readInt();
        lastSeenEpisodeName = in.readString();
        lastSeenEpisodeAirDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(characterId);
        dest.writeString(name);
        dest.writeString(status);
        dest.writeString(species);
        dest.writeString(gender);
        dest.writeString(originLocationName);
        dest.writeString(lastLocationName);
        dest.writeString(image);
        dest.writeInt(numberOfEpisodes);
        dest.writeString(lastSeenEpisodeName);
        dest.writeString(lastSeenEpisodeAirDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CharacterInfo> CREATOR = new Creator<CharacterInfo>() {
        @Override
        public CharacterInfo createFromParcel(Parcel in) {
            return new CharacterInfo(in);
        }

        @Override
        public CharacterInfo[] newArray(int size) {
            return new CharacterInfo[size];
        }
    };

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOriginLocationName() {
        return originLocationName;
    }

    public void setOriginLocationName(String originLocationName) {
        this.originLocationName = originLocationName;
    }

    public String getLastLocationName() {
        return lastLocationName;
    }

    public void setLastLocationName(String lastLocationName) {
        this.lastLocationName = lastLocationName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getLastSeenEpisodeName() {
        return lastSeenEpisodeName;
    }

    public void setLastSeenEpisodeName(String lastSeenEpisodeName) {
        this.lastSeenEpisodeName = lastSeenEpisodeName;
    }

    public String getLastSeenEpisodeAirDate() {
        return lastSeenEpisodeAirDate;
    }

    public void setLastSeenEpisodeAirDate(String lastSeenEpisodeAirDate) {
        this.lastSeenEpisodeAirDate = lastSeenEpisodeAirDate;
    }

    public String recordInfo(){
        return characterId + " - " + name;
    }
}
