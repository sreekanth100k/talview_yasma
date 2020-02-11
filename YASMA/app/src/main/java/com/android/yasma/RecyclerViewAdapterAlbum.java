package com.android.yasma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterAlbum extends RecyclerView.Adapter<RecyclerViewAdapterAlbum.ViewHolder> {

    private List<AlbumPOJO> marketList;


    public RecyclerViewAdapterAlbum() {
        marketList = new ArrayList<>();
    }

    @Override
    public RecyclerViewAdapterAlbum.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_recycler_view_item_layout, parent, false);

        RecyclerViewAdapterAlbum.ViewHolder viewHolder = new RecyclerViewAdapterAlbum.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapterAlbum.ViewHolder holder, int position) {
        AlbumPOJO postPojoObj = marketList.get(position);

//        String id       =   String.valueOf(postPojoObj.Id);
//        holder.txtId.setText(id);
//        String userId   =   String.valueOf(postPojoObj.UserId);
//        holder.txtUserId.setText(userId);
        String body     =   postPojoObj.title;
        holder.txtBody.setText(body);

    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }

    public void setData(List<AlbumPOJO> data) {
        this.marketList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtId;
        public TextView txtUserId;
        public TextView txtBody;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);

//            txtId       = view.findViewById(R.id.txtCoin);
//            txtUserId   = view.findViewById(R.id.txtMarket);
            txtBody     = view.findViewById(R.id.txtPrice);
            cardView    = view.findViewById(R.id.cardView);
        }
    }
}
