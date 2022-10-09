package com.example.movieandroidapp.Utility;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.movieandroidapp.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
    public static String formatDate(String date){
        String dateFormated = "";
        for (int i = 0; i < date.length(); i++) {
            if(date.charAt(i) == 'T'){
                break;
            }
            else{
                dateFormated = dateFormated + date.charAt(i);
            }
        }
        return dateFormated;
    }
}
