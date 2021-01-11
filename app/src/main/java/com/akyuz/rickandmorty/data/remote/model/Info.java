package com.akyuz.rickandmorty.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Info implements Parcelable {
    @SerializedName("count")
    int count;
    @SerializedName("pages")
    int pages;
    @SerializedName("next")
    String next;
    @SerializedName("prev")
    String prev;

    public Info() {
    }

    protected Info(Parcel in) {
        count = in.readInt();
        pages = in.readInt();
        next = in.readString();
        prev = in.readString();
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

    public int getCount() {
        return count;
    }

    public int getPages() {
        return pages;
    }

    public String getNext() {
        return next;
    }

    public String getPrev() {
        return prev;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeInt(pages);
        dest.writeString(next);
        dest.writeString(prev);
    }
}
