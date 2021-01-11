package com.akyuz.rickandmorty.helper;

import android.content.Context;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static String setFavoriteList(List<String> favoriteList) {
        StringBuilder str = new StringBuilder();
        for (String value : favoriteList) {
            str.append(value).append(",");
        }
        return str.toString();
    }

    public static List<String> getFavoriteList(String strFavoriteList) {
        if(strFavoriteList.isEmpty()){
            return new ArrayList<>();
        }else {
            return new ArrayList<>(Arrays.asList(strFavoriteList.split(",")));
        }
    }
    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
}
