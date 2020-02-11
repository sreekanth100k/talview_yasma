package com.android.yasma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPostContent extends RecyclerView.Adapter<RecyclerViewPostContent.ViewHolder> {

    private List<AlbumPhotoContentPOJO> marketList;
    private Context mContext;


    public RecyclerViewPostContent(Context iContext) {
        marketList  =   new ArrayList<>();
        mContext    =   iContext;

    }

    @Override
    public RecyclerViewPostContent.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_recycler_view_item_layout, parent, false);

        RecyclerViewPostContent.ViewHolder viewHolder = new RecyclerViewPostContent.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewPostContent.ViewHolder holder, int position) {
        AlbumPhotoContentPOJO postPojoObj = marketList.get(position);

         String currentItem = String.valueOf(postPojoObj.id);

//        String id       =   String.valueOf(postPojoObj.Id);
//        holder.txtId.setText(id);
//        String userId   =   String.valueOf(postPojoObj.UserId);
//        holder.txtUserId.setText(userId);
        String body     =   postPojoObj.title;
        holder.txtBody.setText(body);

        holder.outerMostLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent albumIntent = new Intent(mContext, AlbumContentActivity.class);
                albumIntent.putExtra("itemId",currentItem);
                mContext.startActivity(albumIntent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }

    public void setData(List<AlbumPhotoContentPOJO> data) {
        this.marketList.addAll(data);
        this.notifyItemRangeChanged(0, marketList.size()-1);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtId;
        public TextView txtUserId;
        public TextView txtBody;
        public CardView cardView;
        public LinearLayout outerMostLl;


        public ViewHolder(View view) {
            super(view);

//            txtId       = view.findViewById(R.id.txtCoin);
//            txtUserId   = view.findViewById(R.id.txtMarket);
            outerMostLl =   view.findViewById(R.id.id_outermost_ll_album_item);

            txtBody     = view.findViewById(R.id.txtPrice);
            cardView    = view.findViewById(R.id.cardView);
        }


    }
}
