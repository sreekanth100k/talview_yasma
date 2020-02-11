package com.android.yasma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private RecyclerViewAdapterPost mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_list_screen);

        String itemId   =   getIntent().getStringExtra("itemId");
        Toast.makeText(this,itemId,Toast.LENGTH_LONG).show();


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

        Call<ResponseBody> call = postServiceObj.getPostListData();
        call.enqueue(new Callback<ResponseBody>() {
                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                             ArrayList<PostPOJO> postPOJOList    =   new ArrayList<PostPOJO>();

                             try {
                                 String responseBody = response.body().string();


//                                 JsonParser parser    = new JsonParser();
//                                 JsonElement array    = (JsonElement) parser.parse(responseBody);
//                                 System.out.println(((JSONObject)array.get(0)).get("user_id"));
                                 JSONArray arrayjs = new JSONArray(responseBody);

                                 for(int i =0;i<arrayjs.length();i++) {
                                     JSONObject jsonObject      =       (JSONObject)arrayjs.get(i);
                                     Integer userId             =       (Integer)jsonObject.get("userId");
                                     Integer id                 =       (Integer)jsonObject.get("id");
                                     String title               =       (String)jsonObject.get("title");
                                     String body                =       (String)jsonObject.get("body");

                                     PostPOJO postPOJOObj       =       new PostPOJO();
                                     postPOJOObj.UserId         =       userId;
                                     postPOJOObj.Body           =       body;
                                     postPOJOObj.Title          =       title;
                                     postPOJOObj.Id             =       id;

                                     postPOJOList.add(postPOJOObj);


                                 }

                                 Log.d("onResponse", responseBody);
                             }catch (Exception e){
                                 Log.e("onResponse", e.getMessage().toString());

                             }

                             handleResults(postPOJOList);


                             if (response.isSuccessful()) {


                                 Log.e("cvbnop",response.body().toString());
                             } else {
                                 Toast.makeText(PostListActivity.this, "Some error occurred...", Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {

                             handleError(t);


                         }
                     }
        );
    }


    private void getReferenceOfViewsAndSetUp(){
        mRecyclerView =   findViewById(R.id.id_posts_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerViewAdapter =   new RecyclerViewAdapterPost(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    /*
     *If things work out load the data into the recycler view.
     */
    private void handleResults(List<PostPOJO> postPOJOList) {
        if (postPOJOList != null && postPOJOList.size() != 0) {
            mRecyclerViewAdapter.setData(postPOJOList);

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
