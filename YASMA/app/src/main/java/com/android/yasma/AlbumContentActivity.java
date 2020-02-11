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

public class AlbumContentActivity extends AppCompatActivity {

    private Retrofit mRetrofit;
    private RecyclerView mAlbumPhotosRv;
    private RecyclerViewAdapterAlbumContent mRecyclerViewAdapterAlbumContentObj;
    private String itemId;
    private TextView userId;
    private TextView id;
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.album_content_activity);

        userId  =   (TextView)findViewById(R.id.id_user_id);

        id      =   (TextView)findViewById(R.id.id_id);

        title   =   (TextView) findViewById(R.id.id_title);

        getReferenceOfViewsAndSetUp();

        mRecyclerViewAdapterAlbumContentObj    =   new RecyclerViewAdapterAlbumContent(this);

        itemId  =   getIntent().getStringExtra("itemId");

        Toast.makeText(this,itemId,Toast.LENGTH_LONG).show();

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

        PostService postServiceObj = mRetrofit.create(PostService.class);

        Call<ResponseBody> call = postServiceObj.getAlbumDetails(Integer.valueOf(itemId));
        call.enqueue(new Callback<ResponseBody>() {
                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                             ArrayList<AlbumPhotoContentPOJO> albumPOJOobjList    =   new ArrayList<AlbumPhotoContentPOJO>();

                             try {
                                 String responseBody = response.body().string();


//                                 JsonParser parser    = new JsonParser();
//                                 JsonElement array    = (JsonElement) parser.parse(responseBody);
//                                 System.out.println(((JSONObject)array.get(0)).get("user_id"));
                                 JSONObject arrayjs = new JSONObject(responseBody);

                                 int userId     =   arrayjs.getInt("userId");
                                 int id         =   arrayjs.getInt("id");
                                 String title   =   arrayjs.getString("title");

                                 AlbumPhotoContentPOJO albumContentPOJOObj   =   new AlbumContentPOJO();
                                 albumContentPOJOObj.albumId           =   userId;
                                 albumContentPOJOObj.id                 =   id;
                                 albumContentPOJOObj.title              =   title;
//                                 for(int i =0;i<arrayjs.length();i++) {
//                                     JSONObject jsonObject      =       (JSONObject)arrayjs.get(i);
//                                     Integer userId             =       (Integer)jsonObject.get("userId");
//                                     Integer id                 =       (Integer)jsonObject.get("id");
//                                     String title               =       (String)jsonObject.get("title");
//                                     String body                =       (String)jsonObject.get("body");
//
//                                     AlbumPOJO albumPOJOObj      =       new AlbumPOJO();
//                                     albumPOJOObj.userId         =       userId;
//                                     albumPOJOObj.title          =       title;
//                                     albumPOJOObj.id             =       id;
//
//                                     albumPOJOobjList.add(albumPOJOObj);
//
//
//                                 }

                                 handleResults(albumContentPOJOObj);

                                 Log.d("onResponse", responseBody);
                             }catch (Exception e){
                                 Log.e("onResponse", e.getMessage().toString());

                             }



                             if (response.isSuccessful()) {


                                 Log.e("cvbnop",response.body().toString());
                             } else {
                                 Toast.makeText(AlbumContentActivity.this, "Some error occurred...", Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {

                             handleError(t);


                         }
                     }
        );

        Call<ResponseBody> albumPhotoDetailsCall = postServiceObj.getAlbumPhotoDetails(Integer.valueOf(itemId));
        albumPhotoDetailsCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                //Handling the photos here...

                ArrayList<AlbumPhotoContentPOJO> albumPhotoContentPojoList    =   new ArrayList<AlbumPhotoContentPOJO>();

                try {
                    String responseBody = response.body().string();

                    JSONArray arrayjs      =   new JSONArray(responseBody);


                    for(int i =0;i<arrayjs.length();i++) {
                        JSONObject jsonObject       =       (JSONObject)arrayjs.get(i);
                        Integer albumId             =       (Integer)jsonObject.getInt("albumId");
                        Integer id                  =       (Integer)jsonObject.get("id");
                        String title                =       (String)jsonObject.get("title");
                        String url                  =       (String)jsonObject.get("url");
                        String thumbNailUrl         =       (String)jsonObject.get("thumbnailUrl");


                        AlbumPhotoContentPOJO albumPhotoContentPOJOObj       =       new AlbumPhotoContentPOJO();
                        albumPhotoContentPOJOObj.albumId         =       albumId;
                        albumPhotoContentPOJOObj.title           =       title;
                        albumPhotoContentPOJOObj.url             =       url;
                        albumPhotoContentPOJOObj.thumbNailUrl    =       thumbNailUrl;
                        albumPhotoContentPOJOObj.id              =       id;


                        albumPhotoContentPojoList.add(albumPhotoContentPOJOObj);

                    }




                    Log.d("onResponse", responseBody);
                }catch (Exception e){
                    Log.e("onResponse", e.getMessage().toString());

                }


                handleResults(albumPhotoContentPojoList);


                if (response.isSuccessful()) {




                    Log.e("cvbnop",response.body().toString());
                } else {
                    Toast.makeText(AlbumContentActivity.this, "Some error occurred...", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }

    /*
     *If things work out load the data into the recycler view.
     */
    private void handleResults(AlbumContentPOJO iAlbumContentPOJO) {
        if (iAlbumContentPOJO != null) {

            String userIdString = String.valueOf(iAlbumContentPOJO);
            userId.setText(userIdString);
            String idString     = String.valueOf(iAlbumContentPOJO.id);
            id.setText(idString);
            String titleString  = iAlbumContentPOJO.title;
            title.setText(titleString);

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

