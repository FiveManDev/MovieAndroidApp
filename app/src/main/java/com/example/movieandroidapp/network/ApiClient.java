package com.example.movieandroidapp.network;




import android.util.Log;

import com.example.movieandroidapp.Utility.DataLocalManager;
import com.example.movieandroidapp.Utility.Extension;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

//  public static final String address = "http://192.168.43.60";
  public static final String address = "http://192.168.10.147";

//    public static final String address = "http://10.0.2.2:5237";
    public static final String BASE_URL = address+"/api/v1/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
         String ACCESS_TOKEN = DataLocalManager.getAccessToken();
         String BearToken = Extension.addBearToToken(ACCESS_TOKEN);
         OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        chain -> {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Accept", "Application/JSON")
                                    .addHeader("Content-Type","application/json;charset=UTF-8")
                                    .addHeader("Authorization", BearToken).build();
                            return chain.proceed(request);
                        }).connectTimeout(240, TimeUnit.SECONDS)
                 .writeTimeout(240, TimeUnit.SECONDS)
                 .readTimeout(240,TimeUnit.SECONDS)
                 .retryOnConnectionFailure(true).build();

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(defaultHttpClient)
                    .build();
        }
        else{
            retrofit = retrofit.newBuilder()
                    .client(defaultHttpClient)
                    .build();
        }
        return retrofit;
    }

}
