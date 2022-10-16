package com.example.movieandroidapp.Utility;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.movieandroidapp.R;
import com.google.gson.Gson;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String[] decodeAccessToken(String ACCESS_TOKEN){
        java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder() ;
        String[] parts = ACCESS_TOKEN.split("\\."); // split out the "parts" (header, payload and signature)
        String headerJson = new String(decoder.decode(parts[0]));
        String payloadJson = new String(decoder.decode(parts[1]));
        String[] decoded = new String[2];
        decoded[0] = headerJson;
        decoded[1]= payloadJson;
        return decoded;
    }

    public static Gson GsonUtil(){
        return new Gson();
    }
}
