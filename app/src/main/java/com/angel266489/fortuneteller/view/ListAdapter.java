package com.angel266489.fortuneteller.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.angel266489.fortuneteller.R;
import com.angel266489.fortuneteller.database.Wish;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Wish> wishes;

    public ListAdapter(List<Wish> wishes) {
        this.wishes = wishes;
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
        viewHolder.wish.setText(wishes.get(position).getWish());

    }

    public void setWishes(List<Wish> wishes){
        this.wishes = wishes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return wishes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView wish;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wish = itemView.findViewById(R.id.wish);

            ImageButton sendEmail = (ImageButton) itemView.findViewById(R.id.email_icon);
            sendEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : Pass Implicit Intent
                }
            });

            ImageButton makeFavorite = (ImageButton) itemView.findViewById(R.id.email_icon);
            makeFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : Pass Implicit Intent
                }
            });

        }


    }
}
