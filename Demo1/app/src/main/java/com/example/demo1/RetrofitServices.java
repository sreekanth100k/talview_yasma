package com.example.demo1;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitServices {

    @GET("http://192.168.1.11:8000/api/v1/get-country-list/")
    Call<CountryCodeResponse> getCountryList();

}
