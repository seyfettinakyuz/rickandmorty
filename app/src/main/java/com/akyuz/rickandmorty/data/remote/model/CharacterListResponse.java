package com.akyuz.rickandmorty.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterListResponse implements Parcelable {
    @SerializedName("info")
    private final Info info;
    @SerializedName("results")
    private final List<Character> characterList;

    protected CharacterListResponse(Parcel in) {
        info = in.readParcelable(Info.class.getClassLoader());
        characterList = in.createTypedArrayList(Character.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(info, flags);
        dest.writeTypedList(characterList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CharacterListResponse> CREATOR = new Creator<CharacterListResponse>() {
        @Override
        public CharacterListResponse createFromParcel(Parcel in) {
            return new CharacterListResponse(in);
        }

        @Override
        public CharacterListResponse[] newArray(int size) {
            return new CharacterListResponse[size];
        }
    };

    public Info getInfo() {
        return info;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }
}
