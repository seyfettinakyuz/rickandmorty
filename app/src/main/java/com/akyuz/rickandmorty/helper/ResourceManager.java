package com.akyuz.rickandmorty.helper;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

public class ResourceManager {
    private static Resources mResources;
    private static Resources.Theme mTheme;

    private ResourceManager() {
    }


    public synchronized static void init(@NonNull Context context) {
        mResources = context.getResources();
        mTheme = context.getTheme();
    }

    public static Drawable getDrawable(@DrawableRes int id){
        if (mResources == null)
            throw new RuntimeException("ResourceManager used before initialized. Make sure you call ResourceManager.init(Context)");

        return ResourcesCompat.getDrawable(mResources,id,mTheme);
    }

    public static int getColor(@ColorRes int id){
        if (mResources == null)
            throw new RuntimeException("ResourceManager used before initialized. Make sure you call ResourceManager.init(Context)");
        return ResourcesCompat.getColor(mResources,id,mTheme);
    }

    public static String getString(@StringRes int id){
        if (mResources == null)
            throw new RuntimeException("ResourceManager used before initialized. Make sure you call ResourceManager.init(Context)");
        return mResources.getString(id);
    }

    public static float getDimension(@DimenRes int id){
        if (mResources == null)
            throw new RuntimeException("ResourceManager used before initialized. Make sure you call ResourceManager.init(Context)");
        return mResources.getDimension(id);
    }

}
