package com.example.appmath2023;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AppCache {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static AppCache instance = null;

    private AppCache(Context context) {
        sharedPreferences = context.getSharedPreferences("app_cache", Context.MODE_PRIVATE);
    }

    public static AppCache getInstance(Context context) {
        if (instance == null) {
            instance = new AppCache(context);
        }
        return instance;
    }

    public void saveDataString(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveDataList(String key, List<String> value) {
        editor = sharedPreferences.edit();
        editor.putStringSet(key, (Set<String>) value);
        editor.commit();
    }

    public String getDataString (String key) {
        return sharedPreferences.getString(key, null);
    }

    public List<String> getDataList(String key) {
        sharedPreferences.getStringSet(key, null);
        List<String> changeList = new ArrayList<>();
        changeList.addAll(sharedPreferences.getStringSet(key, null));
        return changeList;
    }

    public void clearCache() {
        sharedPreferences.edit().clear().commit();
    }
}
