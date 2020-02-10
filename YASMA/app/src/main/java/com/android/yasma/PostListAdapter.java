package com.android.yasma;


import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    private List<PostPOJO> marketList;




    @Override
    public PostListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);

        PostListAdapter.ViewHolder viewHolder = new PostListAdapter.ViewHolder(view);
        return viewHolder;
    }

//    @Override
//    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
////        Crypto.Market market = marketList.get(position);
////        holder.txtCoin.setText(market.coinName);
////        holder.txtMarket.setText(market.market);
////        holder.txtPrice.setText("$" + String.format("%.2f", Double.parseDouble(market.price)));
////        if (market.coinName.equalsIgnoreCase("eth")) {
////            holder.cardView.setCardBackgroundColor(Color.GRAY);
////        } else {
////            holder.cardView.setCardBackgroundColor(Color.GREEN);
////        }
//    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }

    public void setData(List<PostPOJO> iPostPojoList) {
        this.marketList.addAll(iPostPojoList);
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCoin;
        public TextView txtMarket;
        public TextView txtPrice;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);

            txtCoin = view.findViewById(R.id.txtCoin);
            txtMarket = view.findViewById(R.id.txtMarket);
            txtPrice = view.findViewById(R.id.txtPrice);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}