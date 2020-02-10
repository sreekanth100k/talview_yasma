package com.android.yasma;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostService {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("posts")
    Call<ResponseBody> getPostData();



}
