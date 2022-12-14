package com.example.movieandroidapp.Utility;

import android.content.Context;

public class DataLocalManager {
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String USER_ID = "USERID";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences  = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }
    public static void setAccessToken(String token){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(ACCESS_TOKEN, token);
    }
    public static String getAccessToken(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(ACCESS_TOKEN);
    }

    public static void setUserId(String userId){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(USER_ID, userId);
    }
    public static String getUserId(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(USER_ID);
    }
}

