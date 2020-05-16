package com.example.wx_client.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseNetwork {
    private final static String BASE_URL = "http://192.168.43.157:1234/";
    protected static Retrofit retrofit;

    static {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
}
