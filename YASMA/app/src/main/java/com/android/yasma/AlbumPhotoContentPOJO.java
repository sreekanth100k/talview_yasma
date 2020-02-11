package com.android.yasma;

import com.google.gson.annotations.SerializedName;

public class AlbumPhotoContentPOJO {


    @SerializedName("albumId")
    int albumId;

    @SerializedName("id")
    int id;

    @SerializedName("title")
    String title;

    @SerializedName("url")
    String url;

    @SerializedName("thumbnailUrl")
    String thumbNailUrl;

}
