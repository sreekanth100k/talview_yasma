package com.android.yasma;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

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

public class PostContentActivity extends AppCompatActivity {

    private Retrofit mRetrofit;
    private RecyclerView mAlbumPhotosRv;
    private RecyclerViewAdapterAlbumContent mRecyclerViewAdapterAlbumContentObj;
    private String itemId;
    private TextView userId;
    private TextView id;
    private TextView title;
    private TextView body;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.post_content_activity);

        getReferenceOfViewsAndSetUp();

        mRecyclerViewAdapterAlbumContentObj     =   new RecyclerViewAdapterAlbumContent(this);

        itemId                                  =   getIntent().getStringExtra("itemId");


        initRetroFit(initOkHttp(),initGson());

        callEndpoints();
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
     * Initializes the Gson.
     * @return gson Object that was created.
     */
    private Gson initGson(){
        Gson gson                           =   new GsonBuilder().setLenient().create();

        return gson;
    }

    public void getReferenceOfViewsAndSetUp(){

        mAlbumPhotosRv = (RecyclerView)findViewById(R.id.id_album_photos_rv);
        userId  =   (TextView)findViewById(R.id.id_user_id);

        id      =   (TextView)findViewById(R.id.id_id);

        title   =   (TextView) findViewById(R.id.id_title);

        body    =    (TextView) findViewById(R.id.id_body);



    }

    /**
     *Initializes the retrofit object.
     */
    private void initRetroFit(OkHttpClient iOkHttpClient, Gson iGson){
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

        PostService postServiceObj  = mRetrofit.create(PostService.class);

        Call<ResponseBody> call     = postServiceObj.getPostDetails(Integer.parseInt(itemId));
        call.enqueue(new Callback<ResponseBody>() {
                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                             PostPOJO postPOJObj   =   new PostPOJO();

                             try {
                                 String responseBody = response.body().string();

                                 JSONObject obj = new JSONObject(responseBody);

                                 String userId = obj.getString("userId");
                                 String id = obj.getString("id");
                                 String title = obj.getString("title");
                                 String body = obj.getString("body");

                                 postPOJObj.Id = Integer.valueOf(id);
                                 postPOJObj.Title = title;
                                 postPOJObj.Body = body;
                                 postPOJObj.UserId = Integer.valueOf(userId);


                                 Log.d("onResponse", responseBody);
                             }catch (Exception e){
                                 Log.e("onResponse", e.getMessage().toString());

                             }

                             handleResults(postPOJObj);

                             if (response.isSuccessful()) {


                                 Log.e("cvbnop",response.body().toString());
                             } else {
                                 Toast.makeText(PostContentActivity.this, "Some error occurred...", Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {

                             handleError(t);


                         }
                     }
        );

        Call<ResponseBody> postCommentscall     = postServiceObj.getPostCommentDetails(Integer.parseInt(itemId));
        postCommentscall.enqueue(new Callback<ResponseBody>() {
                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

//                             ArrayList<PostPOJO> postPOJOList    =   new ArrayList<PostPOJO>();
//
//                             try {
//                                 String responseBody = response.body().string();
//
//
////                                 JsonParser parser    = new JsonParser();
////                                 JsonElement array    = (JsonElement) parser.parse(responseBody);
////                                 System.out.println(((JSONObject)array.get(0)).get("user_id"));
//                                 JSONArray arrayjs = new JSONArray(responseBody);
//
//                                 for(int i =0;i<arrayjs.length();i++) {
//                                     JSONObject jsonObject      =       (JSONObject)arrayjs.get(i);
//                                     Integer userId             =       (Integer)jsonObject.get("userId");
//                                     Integer id                 =       (Integer)jsonObject.get("id");
//                                     String title               =       (String)jsonObject.get("title");
//                                     String body                =       (String)jsonObject.get("body");
//
//                                     PostPOJO postPOJOObj       =       new PostPOJO();
//                                     postPOJOObj.UserId         =       userId;
//                                     postPOJOObj.Body           =       body;
//                                     postPOJOObj.Title          =       title;
//                                     postPOJOObj.Id             =       id;
//
//                                     postPOJOList.add(postPOJOObj);
//
//
//                                 }
//
//                                 Log.d("onResponse", responseBody);
//                             }catch (Exception e){
//                                 Log.e("onResponse", e.getMessage().toString());
//
//                             }
//
////                             handleResults(postPOJOList);
//
//                             if (response.isSuccessful()) {
//
//
//                                 Log.e("cvbnop",response.body().toString());
//                             } else {
//                                 Toast.makeText(PostContentActivity.this, "Some error occurred...", Toast.LENGTH_LONG).show();
//                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {

                             handleError(t);


                         }
                     }
        );

    }

    /*
     *If things work out load the data into the recycler view.
     */
    private void handleResults(PostPOJO iPostPojoObj) {
        if (iPostPojoObj != null) {

            String userIdString = String.valueOf(iPostPojoObj.UserId);
            userId.setText(userIdString);
            String idString     = String.valueOf(iPostPojoObj.Id);
            id.setText(idString);
            String titleString  = iPostPojoObj.Title;
            title.setText(titleString);
            String bodyString = iPostPojoObj.Body;
            body.setText(bodyString);
   } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }

    }
    /*
     *If things work out load the data into the recycler view.
     */
    private void handleResults(ArrayList<AlbumPhotoContentPOJO> iAlbumPhotoContentList) {
        if (iAlbumPhotoContentList != null && iAlbumPhotoContentList.size()!=0) {
            mRecyclerViewAdapterAlbumContentObj.setData(iAlbumPhotoContentList);

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

