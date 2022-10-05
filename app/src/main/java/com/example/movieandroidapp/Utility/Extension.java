package com.example.movieandroidapp.Utility;

import android.text.TextUtils;
import android.util.Patterns;



public class Extension {

    public static String addBearToToken(String token) {
        return "Bearer " + token;
    }

    public static boolean isEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean isLength(String value){
        return !TextUtils.isEmpty(value) && value.length()>=6;
    }
}
