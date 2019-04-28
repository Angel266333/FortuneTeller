package com.angel266489.fortuneteller;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<ListData> listData;

    public ListAdapter(ArrayList<ListData> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.wish.setText(listData.get(position).getWish());
        viewHolder.icon_email.setImageResource(listData.get(position).getmIconEmail());
        viewHolder.icon_star.setImageResource(listData.get(position).getmStarIcon());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView wish;
        ImageView icon_email;
        ImageView icon_star;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wish = itemView.findViewById(R.id.wish);
            icon_email = itemView.findViewById(R.id.email_icon);
        }


    }
}
