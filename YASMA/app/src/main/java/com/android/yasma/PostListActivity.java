package com.android.yasma;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * This lists the posts
 */
public class PostListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Retrofit mRetrofit;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferenceOfViewsAndSetUp();

        initRetroFit(initOkHttp(),initGson());
        callEndpoints();
    }

    /**
     * Initializes the Gson.
     * @return gson Object that was created.
     */
    private Gson initGson(){
        Gson gson                           =   new GsonBuilder().setLenient().create();

        return gson;
    }

    /**
     *Initializes OkHttp.
     * @return OkHttp object that gets created.
     */
    private OkHttpClient initOkHttp(){
        HttpLoggingInterceptor interceptor  =   new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client                 =   new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return client;
    }

    /**
     *Initializes the retrofit object.
     */
    private void initRetroFit(OkHttpClient iOkHttpClient,Gson iGson){
        mRetrofit                           =   new Retrofit.Builder().baseUrl(PostService.BASE_URL)
                                                .client(iOkHttpClient)
                                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                                .addConverterFactory(GsonConverterFactory.create(iGson))
                                                .build();
    }

    /**
     *Calls the End Points.
     */
    private void callEndpoints() {

        PostService postServiceObj = mRetrofit.create(PostService.class);

        Call<ResponseBody> call = postServiceObj.getPostData();
        call.enqueue(new Callback<ResponseBody>() {
                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                             try {
                                 Log.d("onResponse", response.body().string());
                             }catch (Exception e){

                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {


                         }
                     }
        );




    }


    private void getReferenceOfViewsAndSetUp(){
        mRecyclerView =   findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerViewAdapter =   new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    /*
     *If things work out load the data into the recycler view.
     */
    private void handleResults(List<Crypto.Market> marketList) {
        if (marketList != null && marketList.size() != 0) {
            mRecyclerViewAdapter.setData(marketList);


        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    /*
     *If some error happens handle that.
     */
    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
}
