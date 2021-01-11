package com.akyuz.rickandmorty.data.remote;

import com.akyuz.rickandmorty.R;
import com.akyuz.rickandmorty.helper.ResourceManager;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;

import retrofit2.HttpException;

public class Utils {
    public static String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return ResourceManager.getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return  ResourceManager.getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            return  ResourceManager.getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            if(((HttpException) error).response() != null){
                return Objects.requireNonNull(((HttpException) error).response()).message();
            }else {
                return ResourceManager.getString(R.string.networkError);
            }
        } else {
            return ResourceManager.getString(R.string.unknownError);
        }

    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
