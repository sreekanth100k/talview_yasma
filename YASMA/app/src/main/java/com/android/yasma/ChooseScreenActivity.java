package com.android.yasma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class ChooseScreenActivity extends Activity {

    private Button mAlbumBtn;
    private Button mPostsBtn;


    public void getReferenceOfBtns(){

        mAlbumBtn = findViewById(R.id.id_album_btn);
        mPostsBtn = findViewById(R.id.id_posts_btn);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_screen_activity);

        getReferenceOfBtns();

        mAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent albumIntent = new Intent(ChooseScreenActivity.this, AlbumListActivity.class);
                ChooseScreenActivity.this.startActivity(albumIntent);

            }
        });

        mPostsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent postIntent = new Intent(ChooseScreenActivity.this, PostListActivity.class);
                ChooseScreenActivity.this.startActivity(postIntent);

            }
        });

    }
}
