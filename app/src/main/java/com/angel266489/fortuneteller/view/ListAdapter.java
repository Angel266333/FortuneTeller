package com.angel266489.fortuneteller.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.angel266489.fortuneteller.R;
import com.angel266489.fortuneteller.database.Wish;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Wish> wishes;

    private String email;

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

    public void setEmail(String email) {
        this.email = email;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView wish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wish = itemView.findViewById(R.id.wish);
            context = itemView.getContext();

            ImageButton sendEmail = itemView.findViewById(R.id.email_icon);

            sendEmail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final String mailto = "mailto:" + email +
                            "?cc= " +
                            "&subject=" + Uri.encode("Your fortune") +
                            "&body=" + Uri.encode(wish.getText().toString());

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse(mailto));
                    try {
                        context.startActivity(emailIntent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context, "Could not parse email details to external mail service!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}