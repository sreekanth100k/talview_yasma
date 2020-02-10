package com.android.yasma;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CryptocurrencyService {


    String BASE_URL     =   "https://api.cryptonator.com/api/full/";

//    String POSTS_URL    =   "https://jsonplaceholder.typicode.com/posts/​";

    @GET("{coin}-usd")
    Observable<Crypto> getCoinData(@Path("coin") String coin);

    @GET("{coin}-usd")
    Observable<Crypto> getPostData();


}