package com.example.demo1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static Retrofit retrofit = null;
    private RetrofitFactory(){

    }

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.11:8000/api/v1/get-country-list/").addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
