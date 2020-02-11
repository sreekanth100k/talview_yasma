package com.android.yasma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterPost extends RecyclerView.Adapter<RecyclerViewAdapterPost.ViewHolder> {

    private List<PostPOJO> marketList;
    private Context mContext;


    public RecyclerViewAdapterPost(Context iContext) {
        marketList  =   new ArrayList<>();
        mContext    =   iContext;

    }

    @Override
    public RecyclerViewAdapterPost.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recycler_view_item_layout, parent, false);

        RecyclerViewAdapterPost.ViewHolder viewHolder = new RecyclerViewAdapterPost.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterPost.ViewHolder holder, int position) {
        PostPOJO postPojoObj = marketList.get(position);

        String currentItem  =   String.valueOf(postPojoObj.Id);

//        String id       =   String.valueOf(postPojoObj.Id);
//        holder.txtId.setText(id);
//        String userId   =   String.valueOf(postPojoObj.UserId);
//        holder.txtUserId.setText(userId);
        String body     =   postPojoObj.Body;
        holder.txtBody.setText(body);

        holder.outerMostLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent albumIntent = new Intent(mContext, PostListActivity.class);
                albumIntent.putExtra("itemId",currentItem);
                mContext.startActivity(albumIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }

    public void setData(List<PostPOJO> data) {
        this.marketList.addAll(data);
        notifyDataSetChanged();
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

            outerMostLl =   view.findViewById(R.id.id_outermost_ll_post_item);
            txtBody     =   view.findViewById(R.id.txtPrice);
            cardView    =   view.findViewById(R.id.cardView);
        }


    }
}
