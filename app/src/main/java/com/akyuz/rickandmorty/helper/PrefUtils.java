package com.akyuz.rickandmorty.helper;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.LinkedList;
import java.util.List;

public class PrefUtils {
    final static String JSON_POSTFIX = "_JSON_object";
    private static SharedPreferences mPrefs;
    private static Gson sGson;

    public synchronized static void init(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public synchronized static void init(Context context, Gson gson) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        sGson = gson;
    }

    public static SharedPreferences getPrefs() {
        if (mPrefs == null)
            throw new RuntimeException("PrefUtils used before initialized. Make sure you call PrefUtils.init(Context)");
        return mPrefs;
    }

    public static Gson getGson() {
        if (sGson == null) {
            synchronized (Gson.class) {
                if (sGson == null)
                    sGson = new Gson();
            }
        }
        return sGson;
    }

    public static void setBooleanPref(String key, boolean value) {
        getPrefs().edit().putBoolean(key, value).apply();
    }

    public static boolean getBooleanPref(String key, boolean defaultValue) {
        return getPrefs().getBoolean(key, defaultValue);
    }

    public static void setStringPref(String key, String value) {
        getPrefs().edit().putString(key, value).apply();
    }

    public static String getStringPref(String key, String defValue) {
        return getPrefs().getString(key, defValue);
    }

    public static void setIntPref(String key, int value) {
        getPrefs().edit().putInt(key, value).apply();
    }

    public static int getIntPref(String key, int defValue) {
        return getPrefs().getInt(key, defValue);
    }

    public static void setObjectPref(String key, Object object) {
        String json = getGson().toJson(object);
        setStringPref(key + JSON_POSTFIX, json);
    }

    public static <T> T getObjectPref(String key, Object clazz) {
        String json = getStringPref(key + JSON_POSTFIX, "");
        if (json.equals("")) {
            return null;
        } else {
            T obj = (T) getGson().fromJson(json, (Class) clazz);
            return obj;
        }
    }

    public static <T> List<T> getObjectList(String key, Class<T> cls) {
        String json = getStringPref(key + JSON_POSTFIX, "");
        List<T> list = new LinkedList<>();
        try {

            JsonArray arry = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(getGson().fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void setLongPref(String key, long value) {
        getPrefs().edit().putLong(key, value).apply();
    }

    public static long getLongPref(String key, long defValue) {
        return getPrefs().getLong(key, defValue);
    }

    public static void removeShared(String key) {
        getPrefs().edit().remove(key).apply();
    }
}
