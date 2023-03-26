package com.example.appmath2023;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

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

    public void saveDataList(String key, List<String> data) {
        if (data != null) {
            Gson gson = new Gson();
            String json = gson.toJson(data);
            editor = sharedPreferences.edit();
            editor.putString(key, json);
            editor.commit();
        }
    }

    public String getDataString (String key) {
        return sharedPreferences.getString(key, null);
    }

    public List<String> getDataList(String key) {
        String json = sharedPreferences.getString(key, null);
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void clearCache() {
        sharedPreferences.edit().clear().commit();
    }
}
